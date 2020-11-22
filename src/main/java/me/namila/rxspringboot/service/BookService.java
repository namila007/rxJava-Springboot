package me.namila.rxspringboot.service;

import java.util.List;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import io.reactivex.Single;
import me.namila.rxspringboot.model.Book;
import me.namila.rxspringboot.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

@Service
public class BookService extends GenericServiceImpl<Book, String> {

  private ReactiveMongoTemplate reactiveMongoTemplate;

  public BookService() {
    super(Book.class, String.class);
  }

  public Single<Book> createBook(Book book) {
    if (book.getAuthor().getId() == null) {
      book.getAuthor().setId(new ObjectIdGenerators.UUIDGenerator().generateId(book.getAuthor()).toString());
    }
    if (book.getGenre().getId() == null) {
      book.getGenre().setId(new ObjectIdGenerators.UUIDGenerator().generateId(book.getGenre()).toString());
    }
    return create(book);
  }

  public Single<Book> getBookById(String id) {
    return getById(id);
  }

  public Single<Book> getBookByISBN(String isbn) {
    Query query = new Query(Criteria.where("ISBN").is(isbn));
    return Single.fromPublisher(reactiveMongoTemplate.findOne(query, Book.class)).onErrorResumeNext(
        v -> Single.error(new RuntimeException("Book Error :" + v.getMessage())));
  }

  public Single<List<Book>> getAllBook() {
    return getAll();
  }

  // ToDO : completable error handle using single
  public Single<String> deleteBookById(String id) {
    return deleteById(id);
  }

  @Autowired
  public void setReactiveMongoTemplate(ReactiveMongoTemplate reactiveMongoTemplate) {
    this.reactiveMongoTemplate = reactiveMongoTemplate;
  }

  @Autowired
  public void setBookRepository(BookRepository bookRepository) {
    this.setRepository(bookRepository);
  }
}
