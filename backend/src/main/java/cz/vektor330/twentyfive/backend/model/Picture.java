package cz.vektor330.twentyfive.backend.model;

import java.util.Objects;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "pictures")
public final class Picture {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  @Column(nullable = false, updatable = false)
  private UUID id;

  @Column(nullable = false)
  private String url;

  // TODO refactoring: the position should be unique in a given gallery
  @Column(nullable = false)
  private short position;

  public Picture(final UUID id, final String url, final short position) {
    this.id = id;
    this.url = url;
    this.position = position;
  }

  public Picture() {
  }

  public UUID getId() {
    return id;
  }

  public void setId(final UUID id) {
    this.id = id;
  }

  public String getUrl() {
    return url;
  }

  public void setUrl(final String url) {
    this.url = url;
  }

  public short getPosition() {
    return position;
  }

  public void setPosition(final short position) {
    this.position = position;
  }

  @Override
  public boolean equals(final Object o) {
    if (!(o instanceof final Picture picture)) {
      return false;
    }

    return Objects.equals(id, picture.id) && Objects.equals(url, picture.url) && Objects.equals(position,
        picture.position);
  }

  @Override
  public int hashCode() {
    int result = Objects.hashCode(id);
    result = 31 * result + Objects.hashCode(url);
    result = 37 * result + Objects.hashCode(position);
    return result;
  }

}
