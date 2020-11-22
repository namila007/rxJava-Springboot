package me.namila.rxspringboot.service;

import java.util.List;
import io.reactivex.Single;
import me.namila.rxspringboot.model.Author;
import me.namila.rxspringboot.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthorService {

  private AuthorRepository authorRepository;

  public Single<Author> createAuthor(Author author) {
    return Single.create(sub -> {
      try {
        if (Boolean.FALSE.equals(authorRepository.existsByName(author.getName()).blockingGet())) {
          authorRepository.save(author).subscribe(sub::onSuccess, sub::onError);
        } else {
          sub.onError(new RuntimeException("Author is already registered"));
        }

      } catch (Exception e) {
        sub.onError(e);
      }
    });
  }

  @Autowired
  public void setAuthorRepository(AuthorRepository authorRepository) {
    this.authorRepository = authorRepository;
  }

  public Single<Author> getAuthorById(String id) {
    return authorRepository.findById(id).toSingle();
  }

  public Single<List<Author>> getAllAuthors() {
    return authorRepository.findAll().toList();
  }
}
