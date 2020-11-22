package me.namila.rxspringboot.repository;

import me.namila.rxspringboot.model.Genre;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface GenreRepository extends ReactiveCrudRepository<Genre, String> {
}
