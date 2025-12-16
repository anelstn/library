package library.library.mapper;

import library.library.dto.AuthorDto;
import library.library.model.Author;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import java.util.List;

@Mapper(componentModel = "spring")
public interface AuthorMapper {

    @Mapping(target = "nameDto", source = "name")
    AuthorDto toDto(Author entity);

    @Mapping(target = "name", source = "nameDto")
    Author toEntity(AuthorDto dto);

    List<AuthorDto> toDtoList(List<Author> entities);
}
