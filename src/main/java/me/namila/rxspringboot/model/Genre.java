package me.namila.rxspringboot.model;

import java.util.ArrayList;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "genre")
public class Genre {

  @Id
  private String id;

  private String name;

  @JsonIgnore
  private ArrayList<Book> bookArrayList = new ArrayList<>();

  @CreatedDate
  private Date createdAt;

  @LastModifiedDate
  private Date lastModifiedDate;

  public void setBookArrayList(ArrayList<Book> bookArrayList) {
    this.bookArrayList.clear();
    this.bookArrayList.addAll(bookArrayList);
  }
}
