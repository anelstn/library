package library.library.service;

import library.library.dto.GenreDto;
import java.util.List;

public interface GenreService {
    List<GenreDto> getAll();
    GenreDto getById(Long id);
    boolean add(GenreDto genreDto);
    boolean update(Long id, GenreDto genreDto);
    void delete(Long id);
}
