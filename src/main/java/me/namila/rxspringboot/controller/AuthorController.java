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
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * The type Author controller.
 */
@RestController()
@RequestMapping(value = Routes.BASE_END_POINT + Routes.AUTHOR_BASEURL)
public class AuthorController {

  private AuthorService authorService;

  /**
   * Create author single.
   *
   * @param author the author
   * @return the single
   */
  @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public Single<ResponseEntity<?>> createAuthor(
      @RequestBody
          Author author) {
    return authorService.createAuthor(author).subscribeOn(Schedulers.io()).map(
        result -> new ResponseEntity<Author>(result, HttpStatus.CREATED));
  }

  /**
   * Gets author by id.
   *
   * @param id the id
   * @return the author by id
   */
  @GetMapping(path = Routes.ID_PARAM,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public Single<ResponseEntity<?>> getAuthorById(
      @PathVariable
          String id) {
    return authorService.getAuthorById(id).subscribeOn(Schedulers.io()).map(
        result -> new ResponseEntity<Author>(result, HttpStatus.OK));
  }

  /**
   * Gets all author.
   *
   * @return the all author
   */
  @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  public Single<ResponseEntity<?>> getAllAuthor() {
    return authorService.getAllAuthors().subscribeOn(Schedulers.io()).map(
        result -> new ResponseEntity<List<Author>>(result, HttpStatus.OK));
  }

  /**
   * Gets author by name.
   *
   * @param name the name
   * @return the author by name
   */
  @GetMapping(path = Routes.NAME_BASEURL + Routes.NAME_PARAM,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public Single<ResponseEntity<?>> getAuthorByName(
      @PathVariable
          String name) {
    return authorService.findAuthorByName(name).subscribeOn(Schedulers.io()).map(
        result -> new ResponseEntity<Author>(result, HttpStatus.OK));
  }

  /**
   * Delete author by id single.
   *
   * @param id the id
   * @return the single
   */
  @DeleteMapping(path = Routes.ID_PARAM,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public Single<ResponseEntity<?>> deleteAuthorById(
      @PathVariable
          String id) {
    return authorService.deleteAuthorById(id).subscribeOn(Schedulers.io()).map(
        result -> new ResponseEntity<String>(result, HttpStatus.OK));
  }

  /**
   * Sets author service.
   *
   * @param authorService the author service
   */
  @Autowired
  public void setAuthorService(AuthorService authorService) {
    this.authorService = authorService;
  }
}
