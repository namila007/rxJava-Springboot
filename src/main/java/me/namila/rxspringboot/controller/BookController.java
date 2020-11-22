package me.namila.rxspringboot.controller;

import java.util.List;
import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;
import me.namila.rxspringboot.constant.statics.Routes;
import me.namila.rxspringboot.model.Book;
import me.namila.rxspringboot.service.BookService;
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
 * The type Book controller.
 */
@RestController()
@RequestMapping(value = Routes.BASE_END_POINT + Routes.BOOK_BASEURL,
    produces = MediaType.APPLICATION_JSON_VALUE)
public class BookController {

  /**
   * The Book service.
   */
  public BookService bookService;

  /**
   * Create author single.
   *
   * @param book the book
   * @return the single
   */
  @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
  public Single<ResponseEntity<?>> createAuthor(
      @RequestBody
          Book book) {
    return bookService.createBook(book).subscribeOn(Schedulers.io()).map(
        result -> new ResponseEntity<Book>(result, HttpStatus.CREATED));
  }

  /**
   * Gets book by id.
   *
   * @param id the id
   * @return the book by id
   */
  @GetMapping(path = Routes.ID_PARAM,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public Single<ResponseEntity<?>> getBookById(
      @PathVariable
          String id) {
    return bookService.getBookById(id).subscribeOn(Schedulers.io()).map(
        result -> new ResponseEntity<Book>(result, HttpStatus.OK));
  }

  /**
   * Gets all author.
   *
   * @return the all author
   */
  @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  public Single<ResponseEntity<?>> getAllAuthor() {
    return bookService.getAllBook().subscribeOn(Schedulers.io()).map(
        result -> new ResponseEntity<List<Book>>(result, HttpStatus.OK));
  }

  /**
   * Gets book by isbn.
   *
   * @param ISBN the isbn
   * @return the book by isbn
   */
  @GetMapping(path = Routes.NAME_BASEURL + Routes.ISBN_PARAM,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public Single<ResponseEntity<?>> getBookByISBN(
      @PathVariable
          String ISBN) {
    return bookService.getBookByISBN(ISBN).subscribeOn(Schedulers.io()).map(
        result -> new ResponseEntity<Book>(result, HttpStatus.OK));
  }

  /**
   * Delete book by id single.
   *
   * @param id the id
   * @return the single
   */
  @DeleteMapping(path = Routes.ID_PARAM,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public Single<ResponseEntity<?>> deleteBookById(
      @PathVariable
          String id) {
    return bookService.deleteBookById(id).subscribeOn(Schedulers.io()).map(
        result -> new ResponseEntity<String>(result, HttpStatus.OK));
  }

  /**
   * Sets book service.
   *
   * @param bookService the book service
   */
  @Autowired
  public void setBookService(BookService bookService) {
    this.bookService = bookService;
  }
}
