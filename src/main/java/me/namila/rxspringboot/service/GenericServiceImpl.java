package me.namila.rxspringboot.service;

import java.util.List;
import io.reactivex.Single;
import me.namila.rxspringboot.model.Book;
import me.namila.rxspringboot.repository.GenericRepository;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class GenericServiceImpl<T, K> implements GenericService<T, K> {

  private final Class<T> modelType;
  private final Class<K> keyType;
  private GenericRepository<T, K> repository;

  public GenericServiceImpl(Class<T> type, Class<K> keyType) {
    this.modelType = type;
    this.keyType = keyType;
  }

  @Override
  public Single<T> create(T t) {
    try {
      String checkValue;
      if (!(t instanceof Book)) {
        checkValue = t.getClass().getMethod("getName").invoke(t).toString();
      } else {
        checkValue = t.getClass().getMethod("getIsbn").invoke(t).toString();
      }
      return repository.existsByName(checkValue, this.modelType).flatMap(x -> {
        if (Boolean.FALSE.equals(x)) {
          return (repository.save(t));
        } else {
          return Single.error(new RuntimeException("Author is already registered"));
        }
      }).onErrorResumeNext(x -> Single.error(new RuntimeException("Error Occurred: " + x.getMessage())));
    } catch (Exception e) {
      return Single.error(new RuntimeException(e.getCause() + " " + e.getMessage()));
    }
  }

  @Override
  public Single<List<T>> getAll() {
    return repository.findAll().toList();
  }

  @Override
  public Single<T> getById(K id) {
    return repository.findById(id).toSingle().onErrorResumeNext(
        v -> Single.error(new RuntimeException("Error: " + v.getMessage())));
  }

  @Override
  public Single<T> getByName(String name) {
    return repository.findByName(name, modelType);
  }

  @Override
  public Single<String> deleteById(K id) {
    return repository.deleteById(id).doOnError(e -> Single.error(new RuntimeException("Dd"))).toSingle(() -> ("Ok"));
  }

  @Autowired(required = false)
  public void setRepository(GenericRepository<T, K> repository) {
    this.repository = repository;
  }
}
