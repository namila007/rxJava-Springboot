package me.namila.rxspringboot.repository;

import io.reactivex.Single;
import me.namila.rxspringboot.model.Author;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

public class AuthorCustomRepositoryImpl implements AuthorCustomRepository {

  private final ReactiveMongoTemplate reactiveMongoTemplate;

  @Autowired
  public AuthorCustomRepositoryImpl(ReactiveMongoTemplate reactiveMongoTemplate) {
    this.reactiveMongoTemplate = reactiveMongoTemplate;
  }

  @Override
  public Single<Boolean> existsByName(String name) {
    Query query = new Query(Criteria.where("name").is(name));

    return Single.create(s -> {
      reactiveMongoTemplate.exists(query, Author.class).single().subscribe(v -> {
        if (v) {
          s.onSuccess(Boolean.TRUE);
        } else {
          s.onSuccess(Boolean.FALSE);
        }

      });
    });

  }
}
