package library.library.service;

import library.library.model.Author;
import java.util.List;

public interface AuthorService {
    List<Author> getAll();
    Author getById(Long id);
    void add(Author author);
    void update(Long id, Author author);
    void delete(Long id);
}
