package me.namila.rxspringboot.controller;

import java.util.List;
import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;
import me.namila.rxspringboot.constant.statics.Routes;
import me.namila.rxspringboot.model.Author;
import me.namila.rxspringboot.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping(value = Routes.BASE_END_POINT + Routes.AUTHOR_BASEURL)
public class AuthorController {

  private AuthorService authorService;

  @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public Single<ResponseEntity<?>> createAuthor(
      @RequestBody
          Author author) {
    return authorService.createAuthor(author).subscribeOn(Schedulers.io()).map(
        result -> new ResponseEntity<Author>(result, HttpStatus.CREATED));
  }

  @GetMapping(path = "/{id}",
      produces = MediaType.APPLICATION_JSON_VALUE)
  public Single<ResponseEntity<?>> getAuthorById(
      @PathVariable
          String id) {
    return authorService.getAuthorById(id).subscribeOn(Schedulers.io()).map(
        result -> new ResponseEntity<Author>(result, HttpStatus.OK));
  }

  @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  public Single<ResponseEntity<?>> getAuthorById() {
    return authorService.getAllAuthors().subscribeOn(Schedulers.io()).map(
        result -> new ResponseEntity<List<Author>>(result, HttpStatus.OK));
  }

  @Autowired
  public void setAuthorService(AuthorService authorService) {
    this.authorService = authorService;
  }
}
