package me.namila.rxspringboot.controller;

import java.util.List;
import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;
import me.namila.rxspringboot.constant.statics.Routes;
import me.namila.rxspringboot.model.Genre;
import me.namila.rxspringboot.service.GenreService;
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

@RestController()
@RequestMapping(value = Routes.BASE_END_POINT + Routes.GENRE_BASEURL,
    produces = MediaType.APPLICATION_JSON_VALUE)
public class GenreController {

  private GenreService genreService;

  @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
  public Single<ResponseEntity<?>> createAuthor(
      @RequestBody
          Genre genre) {
    return genreService.createGenre(genre).subscribeOn(Schedulers.io()).map(
        result -> new ResponseEntity<Genre>(result, HttpStatus.CREATED));
  }

  @GetMapping(path = Routes.ID_PARAM,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public Single<ResponseEntity<?>> getAuthorById(
      @PathVariable
          String id) {
    return genreService.getGenreById(id).subscribeOn(Schedulers.io()).map(
        result -> new ResponseEntity<Genre>(result, HttpStatus.OK));
  }

  @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  public Single<ResponseEntity<?>> getAllAuthor() {
    return genreService.getAllGenre().subscribeOn(Schedulers.io()).map(
        result -> new ResponseEntity<List<Genre>>(result, HttpStatus.OK));
  }

  @GetMapping(path = Routes.NAME_BASEURL + Routes.NAME_PARAM,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public Single<ResponseEntity<?>> getAuthorByName(
      @PathVariable
          String name) {
    return genreService.findGenreByName(name).subscribeOn(Schedulers.io()).map(
        result -> new ResponseEntity<Genre>(result, HttpStatus.OK));
  }

  @DeleteMapping(path = Routes.ID_PARAM,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public Single<ResponseEntity<?>> deleteAuthorById(
      @PathVariable
          String id) {
    return genreService.deleteGenreById(id).subscribeOn(Schedulers.io()).map(
        result -> new ResponseEntity<String>(result, HttpStatus.OK));
  }
}
