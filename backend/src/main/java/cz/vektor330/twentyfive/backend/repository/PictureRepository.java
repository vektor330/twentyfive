package cz.vektor330.twentyfive.backend.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cz.vektor330.twentyfive.backend.model.Picture;

@Repository
public interface PictureRepository extends JpaRepository<Picture, UUID> {

}
