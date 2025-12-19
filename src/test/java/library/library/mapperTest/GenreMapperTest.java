package library.library.mapperTest;

import library.library.dto.GenreDto;
import library.library.mapper.GenreMapper;
import library.library.model.Genre;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.List;
import java.util.ArrayList;

@SpringBootTest
public class GenreMapperTest {
    @Autowired
    private GenreMapper genreMapper;

    @Test
    void convertEntityToDtoTest() {
        Genre genre = new Genre(1L, "Dystopian fiction");
        GenreDto dto = genreMapper.toDto(genre);

        Assertions.assertNotNull(dto);
        Assertions.assertEquals(genre.getId(), dto.getId());
        Assertions.assertEquals(genre.getName(), dto.getNameDto());
    }

    @Test
    void convertDtoToEntityTest() {
        GenreDto dto = new GenreDto(1L, "Dystopian fiction");
        Genre entity = genreMapper.toEntity(dto);

        Assertions.assertNotNull(entity);
        Assertions.assertEquals(dto.getId(), entity.getId());
        Assertions.assertEquals(dto.getNameDto(), entity.getName());
    }

    @Test
    void convertEntityListToDtoListTest() {
        List<Genre> list = new ArrayList<>();
        list.add(new Genre(1L, "A"));
        list.add(new Genre(2L, "B"));

        List<GenreDto> dtoList = genreMapper.toDtoList(list);
        Assertions.assertNotNull(dtoList);
        Assertions.assertEquals(list.size(), dtoList.size());
        for (int i = 0; i < list.size(); i++) {
            Assertions.assertEquals(list.get(i).getId(), dtoList.get(i).getId());
            Assertions.assertEquals(list.get(i).getName(), dtoList.get(i).getNameDto());
        }
    }
}
