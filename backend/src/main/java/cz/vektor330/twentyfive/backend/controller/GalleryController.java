package cz.vektor330.twentyfive.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
  public ResponseEntity<GalleryDto> getGallery(
      @RequestParam(required = false) final String user,
      @RequestParam(required = false) final String gallery) {
    // FIXME proceed with passing the user and the gallery into the service
    System.out.println("user: " + user + ", gallery: " + gallery);
    return ResponseEntity.ok(galleryService.get());
  }

}
