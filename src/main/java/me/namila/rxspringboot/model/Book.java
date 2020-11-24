package me.namila.rxspringboot.model;

import java.util.Date;
import java.util.Objects;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "book")
public class Book {
  @Id
  private String id;

  private String name;

  @Indexed
  private String isbn;

  private Author author;

  private Genre genre;

  @CreatedDate
  private Date createdAt;

  @LastModifiedDate
  private Date lastModifiedDate;

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Book book = (Book) o;
    return Objects.equals(id, book.id) && Objects.equals(name, book.name) && Objects.equals(author, book.author)
        && Objects.equals(genre, book.genre);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, author, genre);
  }
}
