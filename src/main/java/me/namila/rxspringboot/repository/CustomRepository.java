package me.namila.rxspringboot.repository;

import io.reactivex.Single;

/**
 * The interface Custom repository.
 *
 * @param <T> the type parameter
 */
public interface CustomRepository<T> {

  /**
   * Exists by name single.
   *
   * @param name      the name
   * @param classType the class type
   * @return the single
   */
  Single<Boolean> existsByName(String name, Class<T> classType);

  /**
   * Find by name single.
   *
   * @param name      the name
   * @param classType the class type
   * @return the single
   */
  Single<T> findByName(String name, Class<T> classType);

}
