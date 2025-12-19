package library.library.serviceTest;

import library.library.dto.AuthorDto;
import library.library.dto.BookDto;
import library.library.dto.GenreDto;
import library.library.service.AuthorService;
import library.library.service.BookService;
import library.library.service.GenreService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Random;

@SpringBootTest
public class BookServiceTest {
    @Autowired
    private BookService bookService;

    @Autowired
    private AuthorService authorService;

    @Autowired
    private GenreService genreService;

    @Test
    void getAllTest() {
        List<BookDto> books = bookService.getAll();
        Assertions.assertNotNull(books);

        for (BookDto dto : books) {
            Assertions.assertNotNull(dto.getId());
            Assertions.assertNotNull(dto.getTitleDto());
            Assertions.assertNotNull(dto.getAuthorDto());
            Assertions.assertNotNull(dto.getGenresDto());
        }
    }

    @Test
    void getByIdTest() {
        AuthorDto author = authorService.add(new AuthorDto(null, "TestAuthor")) ? authorService.getAll().get(0) : null;
        GenreDto genre = genreService.add(new GenreDto(null, "TestGenre")) ? genreService.getAll().get(0) : null;

        BookDto book = new BookDto();
        book.setTitleDto("TestBook");
        book.setPagesDto(100);
        book.setYearDto(2025);
        book.setAuthorDto(author);
        book.setGenresDto(List.of(genre));

        bookService.add(book);

        List<BookDto> allBooks = bookService.getAll();
        Random random = new Random();
        BookDto savedBook = allBooks.get(random.nextInt(allBooks.size()));

        BookDto fetched = bookService.getById(savedBook.getId());
        Assertions.assertNotNull(fetched);
        Assertions.assertEquals(savedBook.getId(), fetched.getId());

        BookDto nonExisting = bookService.getById(-1L);
        Assertions.assertNull(nonExisting);
    }

    @Test
    void addTest() {
        AuthorDto author = new AuthorDto();
        author.setNameDto("AuthorForAddTest");
        authorService.add(author);
        author = authorService.getAll().get(0);

        GenreDto genre = new GenreDto();
        genre.setNameDto("GenreForAddTest");
        genreService.add(genre);
        genre = genreService.getAll().get(0);

        BookDto dto = new BookDto();
        dto.setTitleDto("BookAddTest");
        dto.setPagesDto(123);
        dto.setYearDto(2026);
        dto.setAuthorDto(author);
        dto.setGenresDto(List.of(genre));

        boolean created = bookService.add(dto);
        Assertions.assertTrue(created);

        List<BookDto> books = bookService.getAll();
        boolean found = books.stream().anyMatch(b -> b.getTitleDto().equals("BookAddTest"));
        Assertions.assertTrue(found);
    }

    @Test
    void updateTest() {
        AuthorDto author = new AuthorDto();
        author.setNameDto("AuthorForUpdateTest");
        authorService.add(author);
        author = authorService.getAll().get(0);

        GenreDto genre = new GenreDto();
        genre.setNameDto("GenreForUpdateTest");
        genreService.add(genre);
        genre = genreService.getAll().get(0);

        BookDto dto = new BookDto();
        dto.setTitleDto("BookBeforeUpdate");
        dto.setPagesDto(200);
        dto.setYearDto(2020);
        dto.setAuthorDto(author);
        dto.setGenresDto(List.of(genre));

        bookService.add(dto);

        List<BookDto> books = bookService.getAll();
        BookDto toUpdate = books.get(books.size() - 1);

        toUpdate.setTitleDto("BookAfterUpdate");
        toUpdate.setPagesDto(999);
        toUpdate.setYearDto(2030);

        boolean updated = bookService.update(toUpdate.getId(), toUpdate);
        Assertions.assertTrue(updated);

        BookDto updatedBook = bookService.getById(toUpdate.getId());
        Assertions.assertEquals("BookAfterUpdate", updatedBook.getTitleDto());
        Assertions.assertEquals(999, updatedBook.getPagesDto());
        Assertions.assertEquals(2030, updatedBook.getYearDto());
    }

    @Test
    void deleteTest() {
        AuthorDto author = new AuthorDto();
        author.setNameDto("AuthorForDeleteTest");
        authorService.add(author);
        author = authorService.getAll().get(0);

        GenreDto genre = new GenreDto();
        genre.setNameDto("GenreForDeleteTest");
        genreService.add(genre);
        genre = genreService.getAll().get(0);

        BookDto dto = new BookDto();
        dto.setTitleDto("BookToDelete");
        dto.setPagesDto(150);
        dto.setYearDto(2021);
        dto.setAuthorDto(author);
        dto.setGenresDto(List.of(genre));

        bookService.add(dto);

        List<BookDto> books = bookService.getAll();
        BookDto toDelete = books.get(books.size() - 1);

        bookService.delete(toDelete.getId());
        BookDto deleted = bookService.getById(toDelete.getId());
        Assertions.assertNull(deleted);
    }
}
