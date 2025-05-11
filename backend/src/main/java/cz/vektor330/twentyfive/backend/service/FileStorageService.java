package cz.vektor330.twentyfive.backend.service;

import java.io.File;
import java.io.IOException;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import io.minio.MinioClient;
import io.minio.RemoveObjectArgs;
import io.minio.UploadObjectArgs;

import io.sentry.Sentry;
import io.sentry.SentryLevel;

@Service
public class FileStorageService {

  // TODO refactoring: make the bucket also configurable, perhaps it should participate in the object access URL root
  private static final String BUCKET_NAME = "twentyfive";

  @Value("${app.s3.endpoint}")
  private String s3Endpoint;

  @Value("${app.s3.object_access_url_root}")
  private String objectAccessUrlRoot;

  @Value("${app.s3.key.access}")
  private String s3AccessKey;

  @Value("${app.s3.key.private}")
  private String s3PrivateKey;

  public Optional<String> storeFileToS3(final MultipartFile file) {
    try {
      final String uid = UUID.randomUUID().toString();
      // TODO refactoring: the path in the bucket should be extracted
      final String objectKey = "images/" + uid;

      // TODO refactoring: best practices Re: temp file prefix/suffix?
      final File tempFile = File.createTempFile("upload", "suffix");
      file.transferTo(tempFile);

      // TODO refactoring: don't lose the original file name, somehow
      try (final MinioClient client = MinioClient.builder()
          .endpoint(s3Endpoint)
          .credentials(s3AccessKey, s3PrivateKey)
          .build()) {

        client.uploadObject(UploadObjectArgs.builder()
            .bucket(BUCKET_NAME)
            .object(objectKey)
            .filename(tempFile.getAbsolutePath())
            .build());
      } catch (final Exception e) {
        Sentry.captureException(e);
        return Optional.empty();
      }

      // TODO refactoring: move this to a finally {} section
      if (!tempFile.delete()) {
        Sentry.captureMessage("Could not delete temporary file " + tempFile, SentryLevel.ERROR);
      }

      return Optional.of(objectAccessUrlRoot + "/" + objectKey);
    } catch (final IOException e) {
      Sentry.captureException(e);
    }

    return Optional.empty();
  }

  public void deleteFileFromS3(final String url) {
    try (final MinioClient client = MinioClient.builder()
        .endpoint(s3Endpoint)
        .credentials(s3AccessKey, s3PrivateKey)
        .build()) {
      final String objectKeyFromUrl = getObjectKeyFromUrl(url);
      client.removeObject(RemoveObjectArgs.builder()
          .bucket(BUCKET_NAME)
          .object(objectKeyFromUrl)
          .build());
    } catch (final Exception e) {
      Sentry.captureException(e);
    }
  }

  private String getObjectKeyFromUrl(final String url) {
    // TODO refactoring: we probably need to start storing the picture UUIDs instead of their full URLs
    return url.replace(objectAccessUrlRoot + "/", "");
  }

}
