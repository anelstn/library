package library.library.mapper;

import library.library.dto.BookDto;
import library.library.model.Book;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import java.util.List;

@Mapper(componentModel = "spring", uses = {AuthorMapper.class, GenreMapper.class})
public interface BookMapper {

    @Mapping(target = "titleDto", source = "title")
    @Mapping(target = "pagesDto", source = "pages")
    @Mapping(target = "yearDto", source = "year")
    @Mapping(target = "authorDto", source = "author")
    @Mapping(target = "genresDto", source = "genres")
    BookDto toDto(Book entity);

    @Mapping(target = "title", source = "titleDto")
    @Mapping(target = "pages", source = "pagesDto")
    @Mapping(target = "year", source = "yearDto")
    @Mapping(target = "author", source = "authorDto")
    @Mapping(target = "genres", source = "genresDto")
    Book toEntity(BookDto dto);

    List<BookDto> toDtoList(List<Book> entities);
}
