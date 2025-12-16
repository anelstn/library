package library.library.service.impl;

import library.library.model.Genre;
import library.library.repository.GenreRepository;
import library.library.service.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GenreServiceImpl implements GenreService {

    @Autowired
    private GenreRepository genreRepository;

    @Override
    public List<Genre> getAll() {
        return genreRepository.findAll();
    }

    @Override
    public Genre getById(Long id) {
        return genreRepository.findById(id).orElse(null);
    }

    @Override
    public void add(Genre genre) {
        genreRepository.save(genre);
    }

    @Override
    public void update(Long id, Genre genre) {
        Genre existing = genreRepository.findById(id).orElse(null);
        if (existing != null) {
            existing.setName(genre.getName());
            genreRepository.save(existing);
        }
    }

    @Override
    public void delete(Long id) {
        genreRepository.deleteById(id);
    }
}
