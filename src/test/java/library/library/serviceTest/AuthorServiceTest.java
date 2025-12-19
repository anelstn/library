package library.library.serviceTest;

import library.library.dto.AuthorDto;
import library.library.service.AuthorService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.List;

@SpringBootTest
public class AuthorServiceTest {
    @Autowired
    private AuthorService authorService;

    @Test
    void getAllTest() {
        List<AuthorDto> authors = authorService.getAll();
        Assertions.assertNotNull(authors);
        Assertions.assertTrue(authors.size() > 0);
    }

    @Test
    void getByIdTest() {
        List<AuthorDto> authors = authorService.getAll();
        Assertions.assertFalse(authors.isEmpty());

        AuthorDto randomAuthor = authors.get(0);
        Long id = randomAuthor.getId();

        AuthorDto author = authorService.getById(id);
        Assertions.assertNotNull(author);
        Assertions.assertEquals(id, author.getId());

        AuthorDto nonExisting = authorService.getById(-1L);
        Assertions.assertNull(nonExisting);
    }

    @Test
    void addTest() {
        AuthorDto dto = new AuthorDto();
        dto.setNameDto("TestAuthor");

        boolean created = authorService.add(dto);
        Assertions.assertTrue(created);

        List<AuthorDto> authors = authorService.getAll();
        boolean exists = authors.stream().anyMatch(a -> "TestAuthor".equals(a.getNameDto()));
        Assertions.assertTrue(exists);
    }

    @Test
    void updateTest() {
        AuthorDto dto = new AuthorDto();
        dto.setNameDto("OriginalName");
        authorService.add(dto);

        List<AuthorDto> authors = authorService.getAll();
        Long id = authors.stream().filter(a -> "OriginalName".equals(a.getNameDto()))
                .findFirst().get().getId();

        dto.setNameDto("UpdatedName");
        boolean updated = authorService.update(id, dto);
        Assertions.assertTrue(updated);

        AuthorDto updatedDto = authorService.getById(id);
        Assertions.assertEquals("UpdatedName", updatedDto.getNameDto());
    }

    @Test
    void deleteTest() {
        AuthorDto dto = new AuthorDto();
        dto.setNameDto("ToDelete");
        authorService.add(dto);

        List<AuthorDto> authors = authorService.getAll();
        Long id = authors.stream().filter(a -> "ToDelete".equals(a.getNameDto()))
                .findFirst().get().getId();

        authorService.delete(id);

        AuthorDto deleted = authorService.getById(id);
        Assertions.assertNull(deleted);
    }
}
