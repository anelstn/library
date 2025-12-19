package library.library.serviceTest;

import library.library.dto.GenreDto;
import library.library.service.GenreService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.List;

@SpringBootTest
public class GenreServiceTest {
    @Autowired
    private GenreService genreService;

    @Test
    void getAllTest() {
        List<GenreDto> genres = genreService.getAll();
        Assertions.assertNotNull(genres);
        Assertions.assertFalse(genres.isEmpty());
    }

    @Test
    void getByIdTest() {
        List<GenreDto> genres = genreService.getAll();
        Assertions.assertFalse(genres.isEmpty());

        GenreDto randomGenre = genres.get(0);
        Long id = randomGenre.getId();

        GenreDto genre = genreService.getById(id);
        Assertions.assertNotNull(genre);
        Assertions.assertEquals(id, genre.getId());

        GenreDto nonExisting = genreService.getById(-1L);
        Assertions.assertNull(nonExisting);
    }

    @Test
    void addTest() {
        GenreDto dto = new GenreDto();
        dto.setNameDto("TestGenre");

        boolean created = genreService.add(dto);
        Assertions.assertTrue(created);

        List<GenreDto> genres = genreService.getAll();
        boolean exists = genres.stream().anyMatch(g -> "TestGenre".equals(g.getNameDto()));
        Assertions.assertTrue(exists);
    }

    @Test
    void updateTest() {
        GenreDto dto = new GenreDto();
        dto.setNameDto("OriginalGenre");
        genreService.add(dto);

        List<GenreDto> genres = genreService.getAll();
        Long id = genres.stream().filter(g -> "OriginalGenre".equals(g.getNameDto()))
                .findFirst().get().getId();

        dto.setNameDto("UpdatedGenre");
        boolean updated = genreService.update(id, dto);
        Assertions.assertTrue(updated);

        GenreDto updatedDto = genreService.getById(id);
        Assertions.assertEquals("UpdatedGenre", updatedDto.getNameDto());
    }

    @Test
    void deleteTest() {
        GenreDto dto = new GenreDto();
        dto.setNameDto("ToDeleteGenre");
        genreService.add(dto);

        List<GenreDto> genres = genreService.getAll();
        Long id = genres.stream().filter(g -> "ToDeleteGenre".equals(g.getNameDto()))
                .findFirst().get().getId();

        genreService.delete(id);

        GenreDto deleted = genreService.getById(id);
        Assertions.assertNull(deleted);
    }
}
