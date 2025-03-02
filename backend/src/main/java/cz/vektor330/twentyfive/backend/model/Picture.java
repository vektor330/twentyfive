package cz.vektor330.twentyfive.backend.model;

import java.util.Objects;

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
  private String id;

  @Column(nullable = false)
  private String url;

  public Picture(final String id, final String url) {
    this.id = id;
    this.url = url;
  }

  public Picture() {
  }

  public String getId() {
    return id;
  }

  public void setId(final String id) {
    this.id = id;
  }

  public String getUrl() {
    return url;
  }

  public void setUrl(final String url) {
    this.url = url;
  }

  @Override
  public boolean equals(final Object o) {
    if (!(o instanceof final Picture picture)) {
      return false;
    }

    return Objects.equals(id, picture.id) && Objects.equals(url, picture.url);
  }

  @Override
  public int hashCode() {
    int result = Objects.hashCode(id);
    result = 31 * result + Objects.hashCode(url);
    return result;
  }

}
