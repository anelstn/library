package library.library.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookDto {
    private Long id;
    private String titleDto;
    private int pagesDto;
    private int yearDto;
    private AuthorDto authorDto;
    private List<GenreDto> genresDto;
}
