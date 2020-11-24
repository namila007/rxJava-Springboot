package me.namila.rxspringboot.service;

import java.util.List;
import io.reactivex.Single;

/**
 * The interface Generic service.
 *
 * @param <T> the type parameter
 * @param <K> the type parameter
 */
public interface GenericService<T, K> {
  /**
   * Create single.
   *
   * @param t the t
   * @return the single
   */
  Single<T> create(T t);

  /**
   * Gets all.
   *
   * @return the all
   */
  Single<List<T>> getAll();

  /**
   * Gets by id.
   *
   * @param id the id
   * @return the by id
   */
  Single<T> getById(K id);

  /**
   * Gets by name.
   *
   * @param name the name
   * @return the by name
   */
  Single<T> getByName(String name);

  /**
   * Delete by id single.
   *
   * @param id the id
   * @return the single
   */
  Single<String> deleteById(K id);
}
