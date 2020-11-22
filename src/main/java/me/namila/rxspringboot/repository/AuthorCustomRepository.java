package me.namila.rxspringboot.repository;

import io.reactivex.Single;

public interface AuthorCustomRepository {

  Single<Boolean> existsByName(String name);
}
