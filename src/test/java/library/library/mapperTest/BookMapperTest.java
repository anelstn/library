package library.library.mapperTest;

import library.library.dto.AuthorDto;
import library.library.dto.BookDto;
import library.library.dto.GenreDto;
import library.library.mapper.BookMapper;
import library.library.model.Author;
import library.library.model.Book;
import library.library.model.Genre;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.List;
import java.util.ArrayList;

@SpringBootTest
public class BookMapperTest {
    @Autowired
    private BookMapper bookMapper;

    @Test
    void convertEntityToDtoTest() {
        Author author = new Author(1L, "George Orwell");
        List<Genre> genres = List.of(new Genre(1L, "Dystopian fiction"));
        Book book = new Book(1L, "1984", 328, 1949, author, genres);

        BookDto dto = bookMapper.toDto(book);
        Assertions.assertNotNull(dto);
        Assertions.assertEquals(book.getId(), dto.getId());
        Assertions.assertEquals(book.getTitle(), dto.getTitleDto());
        Assertions.assertEquals(book.getPages(), dto.getPagesDto());
        Assertions.assertEquals(book.getYear(), dto.getYearDto());
        Assertions.assertEquals(book.getAuthor().getId(), dto.getAuthorDto().getId());
        Assertions.assertEquals(book.getGenres().size(), dto.getGenresDto().size());
    }

    @Test
    void convertDtoToEntityTest() {
        AuthorDto authorDto = new AuthorDto(1L, "George Orwell");
        List<GenreDto> genresDto = List.of(new GenreDto(1L, "Dystopian fiction"));
        BookDto dto = new BookDto(1L, "1984", 328, 1949, authorDto, genresDto);

        Book book = bookMapper.toEntity(dto);
        Assertions.assertNotNull(book);
        Assertions.assertEquals(dto.getId(), book.getId());
        Assertions.assertEquals(dto.getTitleDto(), book.getTitle());
        Assertions.assertEquals(dto.getPagesDto(), book.getPages());
        Assertions.assertEquals(dto.getYearDto(), book.getYear());
        Assertions.assertEquals(dto.getAuthorDto().getId(), book.getAuthor().getId());
        Assertions.assertEquals(dto.getGenresDto().size(), book.getGenres().size());
    }

    @Test
    void convertEntityListToDtoListTest() {
        Author author = new Author(1L, "George Orwell");
        List<Genre> genres = List.of(new Genre(1L, "Dystopian fiction"));
        List<Book> books = new ArrayList<>();
        books.add(new Book(1L, "1984", 328, 1949, author, genres));
        books.add(new Book(2L, "Animal Farm", 112, 1945, author, genres));

        List<BookDto> dtoList = bookMapper.toDtoList(books);
        Assertions.assertNotNull(dtoList);
        Assertions.assertEquals(books.size(), dtoList.size());
    }
}
