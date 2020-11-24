package me.namila.rxspringboot.service;

import java.util.List;
import io.reactivex.Single;
import me.namila.rxspringboot.model.Author;
import me.namila.rxspringboot.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * The type Author service.
 */
@Service
public class AuthorService extends GenericServiceImpl<Author, String> {

  /**
   * Instantiates a new Author service.
   */
  public AuthorService() {
    super(Author.class, String.class);
  }

  /**
   * Create author single.
   *
   * @param author the author
   * @return the single
   */
  public Single<Author> createAuthor(Author author) {
    return create(author);
  }

  /**
   * Gets author by id.
   *
   * @param id the id
   * @return the author by id
   */
  public Single<Author> getAuthorById(String id) {
    return getById(id);
  }

  /**
   * Gets all authors.
   *
   * @return the all authors
   */
  public Single<List<Author>> getAllAuthors() {
    return getAll();
  }

  /**
   * Find author by name single.
   *
   * @param name the name
   * @return the single
   */
  public Single<Author> findAuthorByName(String name) {
    return getByName(name).onErrorResumeNext(v -> Single.error(new RuntimeException("Author not found")));
  }

  /**
   * Delete author by id single.
   *
   * @param id the id
   * @return the single
   */
  // ToDO : completable error handle using single
  public Single<String> deleteAuthorById(String id) {
    return deleteById(id);
  }

  /**
   * Sets author repository.
   *
   * @param authorRepository the author repository
   */
  @Autowired
  public void setAuthorRepository(AuthorRepository authorRepository) {
    this.setRepository(authorRepository);
  }
}
