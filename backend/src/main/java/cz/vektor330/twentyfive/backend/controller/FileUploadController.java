package cz.vektor330.twentyfive.backend.controller;

import cz.vektor330.twentyfive.backend.model.Picture;
import cz.vektor330.twentyfive.backend.service.FileStorageService;
import cz.vektor330.twentyfive.backend.service.GalleryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

@RestController
public class FileUploadController {

  private final FileStorageService fileStorageService;

  private final GalleryService galleryService;

  @Autowired
  public FileUploadController(
      final FileStorageService fileStorageService,
      final GalleryService galleryService) {
    this.fileStorageService = fileStorageService;
    this.galleryService = galleryService;
  }

  @PostMapping("/upload")
  public ResponseEntity<?> uploadImage(
      @RequestParam("file") final MultipartFile file,
      @RequestParam("position") final short position) {
    // Validate position range
    // TODO refactoring: consider using Spring Validation or something for this
    if (position < 1 || position > 25) {
      return ResponseEntity
          .badRequest()
          .body("Position must be between 1 and 25");
    }
    
    try {
      // TODO refactoring: proper logging system
      System.out.println("uploading to gallery to position " + position);
      // Store the file and get the URL
      final String fileUrl = fileStorageService.storeFileToS3(file).orElseThrow();
      
      // Update the picture in the gallery at the specified position
      final Picture savedPicture = galleryService.replacePictureAtPosition(position, fileUrl);

      if (savedPicture == null) {
        return ResponseEntity
            .status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body("Failed to update gallery position");
      }
      
      final Map<String, Object> response = new HashMap<>();
      response.put("message", "Picture uploaded successfully");
      response.put("id", savedPicture.getId());
      response.put("url", savedPicture.getUrl());
      response.put("position", savedPicture.getPosition());
      
      return ResponseEntity.ok(response);
    } catch (final Exception e) {
      return ResponseEntity
          .status(HttpStatus.INTERNAL_SERVER_ERROR)
          .body("Failed to upload file: " + e.getMessage());
    }
  }
}