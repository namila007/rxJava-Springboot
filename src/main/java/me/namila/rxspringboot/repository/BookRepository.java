package me.namila.rxspringboot.repository;

import me.namila.rxspringboot.model.Book;
import org.springframework.stereotype.Repository;

/**
 * The interface Book repository.
 */
@Repository
public interface BookRepository extends GenericRepository<Book, String> {
}
