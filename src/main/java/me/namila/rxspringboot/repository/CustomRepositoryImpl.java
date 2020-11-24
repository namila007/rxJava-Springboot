package me.namila.rxspringboot.repository;

import io.reactivex.Single;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

/**
 * The type Custom repository.
 *
 * @param <T> the type parameter
 */
public class CustomRepositoryImpl<T> implements CustomRepository<T> {

  private final ReactiveMongoTemplate reactiveMongoTemplate;

  /**
   * Instantiates a new Custom repository.
   *
   * @param reactiveMongoTemplate the reactive mongo template
   */
  @Autowired
  public CustomRepositoryImpl(ReactiveMongoTemplate reactiveMongoTemplate) {
    this.reactiveMongoTemplate = reactiveMongoTemplate;
  }

  @Override
  public Single<Boolean> existsByName(String name, Class<T> classType) {
    Query query = new Query(Criteria.where("name").is(name));

    return Single.create(s -> {
      reactiveMongoTemplate.exists(query, classType).single().subscribe(v -> {
        if (Boolean.TRUE.equals(v)) {
          s.onSuccess(Boolean.TRUE);
        } else {
          s.onSuccess(Boolean.FALSE);
        }

      });
    });

  }

  @Override
  public Single<T> findByName(String name, Class<T> classType) {
    Query query = new Query(Criteria.where("name").is(name));
    return Single.fromPublisher(reactiveMongoTemplate.findOne(query, classType));
  }

}
