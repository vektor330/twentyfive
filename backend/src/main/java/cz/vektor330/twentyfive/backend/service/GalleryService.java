package cz.vektor330.twentyfive.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cz.vektor330.twentyfive.backend.model.GalleryDto;
import cz.vektor330.twentyfive.backend.repository.PictureRepository;

@Service
public class GalleryService {

  private final PictureRepository pictureRepository;

  @Autowired
  public GalleryService(final PictureRepository pictureRepository) {
    this.pictureRepository = pictureRepository;
  }

  public GalleryDto get() {
    return new GalleryDto(pictureRepository.findAll());
  }

}
