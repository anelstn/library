package library.library.mapper;

import library.library.dto.GenreDto;
import library.library.model.Genre;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import java.util.List;

@Mapper(componentModel = "spring")
public interface GenreMapper {

    @Mapping(target = "nameDto", source = "name")
    GenreDto toDto(Genre entity);

    @Mapping(target = "name", source = "nameDto")
    Genre toEntity(GenreDto dto);

    List<GenreDto> toDtoList(List<Genre> entities);
}
