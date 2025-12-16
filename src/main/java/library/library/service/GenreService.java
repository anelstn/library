package library.library.service;

import library.library.model.Genre;
import java.util.List;

public interface GenreService {
    List<Genre> getAll();
    Genre getById(Long id);
    void add(Genre genre);
    void update(Long id, Genre genre);
    void delete(Long id);
}
