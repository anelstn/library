package library.library.service;

import library.library.dto.BookDto;
import java.util.List;

public interface BookService {
    List<BookDto> getAll();
    BookDto getById(Long id);
    boolean add(BookDto bookDto);
    boolean update(Long id, BookDto bookDto);
    void delete(Long id);
}
