package me.namila.rxspringboot.service;

import java.util.List;
import io.reactivex.Single;
import me.namila.rxspringboot.model.Genre;
import me.namila.rxspringboot.repository.GenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * The type Genre service.
 */
@Service
public class GenreService extends GenericServiceImpl<Genre, String> {

  /**
   * Instantiates a new Genre service.
   */
  public GenreService() {
    super(Genre.class, String.class);
  }

  /**
   * Create genre single.
   *
   * @param genre the genre
   * @return the single
   */
  public Single<Genre> createGenre(Genre genre) {
    return create(genre);
  }

  /**
   * Gets genre by id.
   *
   * @param id the id
   * @return the genre by id
   */
  public Single<Genre> getGenreById(String id) {
    return getById(id);
  }

  /**
   * Gets all genre.
   *
   * @return the all genre
   */
  public Single<List<Genre>> getAllGenre() {
    return getAll();
  }

  /**
   * Find genre by name single.
   *
   * @param name the name
   * @return the single
   */
  public Single<Genre> findGenreByName(String name) {
    return getByName(name).onErrorResumeNext(v -> Single.error(new RuntimeException("Genre not found")));
  }

  /**
   * Delete genre by id single.
   *
   * @param id the id
   * @return the single
   */
  // ToDO : completable error handle using single
  public Single<String> deleteGenreById(String id) {
    return deleteById(id);
  }

  /**
   * Sets genre repository.
   *
   * @param genreRepository the genre repository
   */
  @Autowired
  public void setGenreRepository(GenreRepository genreRepository) {
    this.setRepository(genreRepository);
  }
}
