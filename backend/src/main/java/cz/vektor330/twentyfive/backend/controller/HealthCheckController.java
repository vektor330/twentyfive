package cz.vektor330.twentyfive.backend.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import cz.vektor330.twentyfive.backend.service.GalleryService;

import io.sentry.Sentry;

@RestController
public class HealthCheckController {

  private final GalleryService galleryService;

  public HealthCheckController(final GalleryService galleryService) {
    this.galleryService = galleryService;
  }

  @GetMapping("/health")
  public ResponseEntity<String> health() {
    try {
      galleryService.get();
      return ResponseEntity.ok("All OK!");
    } catch (final Exception e) {
      Sentry.captureException(e);
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error, check logs");
    }
  }

}