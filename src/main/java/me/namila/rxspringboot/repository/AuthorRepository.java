package me.namila.rxspringboot.repository;

import me.namila.rxspringboot.model.Author;
import org.springframework.stereotype.Repository;

/**
 * The interface Author repository.
 */
@Repository
public interface AuthorRepository extends GenericRepository<Author, String> {
}
