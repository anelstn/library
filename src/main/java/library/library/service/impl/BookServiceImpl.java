package library.library.service.impl;

import library.library.model.Book;
import library.library.repository.BookRepository;
import library.library.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository bookRepository;

    @Override
    public List<Book> getAll() {
        return bookRepository.findAll();
    }

    @Override
    public Book getById(Long id) {
        return bookRepository.findById(id).orElse(null);
    }

    @Override
    public void add(Book book) {
        bookRepository.save(book);
    }

    @Override
    public void update(Long id, Book book) {
        Book existing = bookRepository.findById(id).orElse(null);
        if (existing != null) {
            existing.setTitle(book.getTitle());
            existing.setPages(book.getPages());
            existing.setYear(book.getYear());
            existing.setAuthor(book.getAuthor());
            existing.setGenres(book.getGenres());
            bookRepository.save(existing);
        }
    }

    @Override
    public void delete(Long id) {
        bookRepository.deleteById(id);
    }
}
