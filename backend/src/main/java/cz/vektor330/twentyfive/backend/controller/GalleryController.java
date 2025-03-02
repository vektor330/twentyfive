package cz.vektor330.twentyfive.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import cz.vektor330.twentyfive.backend.model.GalleryDto;
import cz.vektor330.twentyfive.backend.service.GalleryService;

@RestController
public class GalleryController {

  private final GalleryService galleryService;

  @Autowired
  public GalleryController(final GalleryService galleryService) {
    this.galleryService = galleryService;
  }

  @GetMapping("/gallery")
  public GalleryDto getGallery() {
    return galleryService.get();
  }

}
