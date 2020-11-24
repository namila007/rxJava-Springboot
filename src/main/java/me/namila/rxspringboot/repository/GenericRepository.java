package me.namila.rxspringboot.repository;

import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.reactive.RxJava2CrudRepository;

/**
 * The interface Generic repository.
 *
 * @param <T> the Model type parameter
 * @param <E> the ID type parameter
 */
@NoRepositoryBean
public interface GenericRepository<T, E> extends RxJava2CrudRepository<T, E>, CustomRepository<T> {
}
