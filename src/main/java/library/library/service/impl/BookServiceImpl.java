package library.library.service.impl;

import library.library.dto.BookDto;
import library.library.mapper.BookMapper;
import library.library.model.Book;
import library.library.model.Author;
import library.library.model.Genre;
import library.library.repository.BookRepository;
import library.library.repository.AuthorRepository;
import library.library.repository.GenreRepository;
import library.library.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository repository;
    private final AuthorRepository authorRepository;
    private final GenreRepository genreRepository;
    private final BookMapper mapper;

    @Override
    public List<BookDto> getAll() {
        return mapper.toDtoList(repository.findAll());
    }

    @Override
    public BookDto getById(Long id) {
        return mapper.toDto(repository.findById(id).orElse(null));
    }

    @Override
    public boolean add(BookDto bookDto) {
        if (Objects.isNull(bookDto)) return false;
        Book book = new Book();
        book.setTitle(bookDto.getTitleDto());
        book.setPages(bookDto.getPagesDto());
        book.setYear(bookDto.getYearDto());

        Author author = authorRepository.findById(bookDto.getAuthorDto().getId()).orElse(null);
        if (author == null) return false;
        book.setAuthor(author);
        List<Genre> genres = bookDto.getGenresDto().stream()
                .map(dto -> genreRepository.findById(dto.getId()).orElse(null))
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
        book.setGenres(genres);
        repository.save(book);
        return true;
    }

    @Override
    public boolean update(Long id, BookDto bookDto) {
        Book book = repository.findById(id).orElse(null);
        if (book == null || bookDto == null) return false;

        book.setTitle(bookDto.getTitleDto());
        book.setPages(bookDto.getPagesDto());
        book.setYear(bookDto.getYearDto());

        Author author = authorRepository.findById(bookDto.getAuthorDto().getId()).orElse(null);
        if (author != null) book.setAuthor(author);
        List<Genre> genres = bookDto.getGenresDto().stream()
                .map(dto -> genreRepository.findById(dto.getId()).orElse(null))
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
        book.setGenres(genres);
        repository.save(book);
        return true;
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
