package me.namila.rxspringboot.repository;

import me.namila.rxspringboot.model.Author;
import org.springframework.data.repository.reactive.RxJava2CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends RxJava2CrudRepository<Author, String>, AuthorCustomRepository {

}
