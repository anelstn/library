package library.library.mapperTest;

import library.library.dto.AuthorDto;
import library.library.mapper.AuthorMapper;
import library.library.model.Author;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.List;
import java.util.ArrayList;

@SpringBootTest
public class AuthorMapperTest {
    @Autowired
    private AuthorMapper authorMapper;

    @Test
    void convertEntityToDtoTest() {
        Author author = new Author(1L, "George Orwell");
        AuthorDto dto = authorMapper.toDto(author);

        Assertions.assertNotNull(dto);
        Assertions.assertEquals(author.getId(), dto.getId());
        Assertions.assertEquals(author.getName(), dto.getNameDto());
    }

    @Test
    void convertDtoToEntityTest() {
        AuthorDto dto = new AuthorDto(1L, "George Orwell");
        Author entity = authorMapper.toEntity(dto);

        Assertions.assertNotNull(entity);
        Assertions.assertEquals(dto.getId(), entity.getId());
        Assertions.assertEquals(dto.getNameDto(), entity.getName());
    }

    @Test
    void convertEntityListToDtoListTest() {
        List<Author> list = new ArrayList<>();
        list.add(new Author(1L, "A"));
        list.add(new Author(2L, "B"));

        List<AuthorDto> dtoList = authorMapper.toDtoList(list);
        Assertions.assertNotNull(dtoList);
        Assertions.assertEquals(list.size(), dtoList.size());
        for (int i = 0; i < list.size(); i++) {
            Assertions.assertEquals(list.get(i).getId(), dtoList.get(i).getId());
            Assertions.assertEquals(list.get(i).getName(), dtoList.get(i).getNameDto());
        }
    }
}
