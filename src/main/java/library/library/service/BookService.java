package library.library.service;

import library.library.model.Book;
import java.util.List;

public interface BookService {
    List<Book> getAll();
    Book getById(Long id);
    void add(Book book);
    void update(Long id, Book book);
    void delete(Long id);
}
