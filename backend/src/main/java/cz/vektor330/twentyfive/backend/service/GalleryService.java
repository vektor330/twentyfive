package cz.vektor330.twentyfive.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cz.vektor330.twentyfive.backend.model.GalleryDto;
import cz.vektor330.twentyfive.backend.model.Picture;
import cz.vektor330.twentyfive.backend.repository.PictureRepository;

@Service
@Transactional
public class GalleryService {

  private final FileStorageService fileStorageService;

  private final PictureRepository pictureRepository;

  @Autowired
  public GalleryService(
      final FileStorageService fileStorageService,
      final PictureRepository pictureRepository) {
    this.fileStorageService = fileStorageService;
    this.pictureRepository = pictureRepository;
  }

  public GalleryDto get() {
    return new GalleryDto(pictureRepository.findAll(Sort.by(Sort.Order.asc("position"))));
  }

  public Picture replacePictureAtPosition(final short position, final String fileUrl) {
    pictureRepository.getPictureByPosition(position).ifPresent(picture -> {
      fileStorageService.deleteFileFromS3(picture.getUrl());
      pictureRepository.delete(picture);
    });

    return pictureRepository.save(new Picture(null, fileUrl, position));
  }

}
