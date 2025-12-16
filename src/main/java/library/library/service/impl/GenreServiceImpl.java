package library.library.service.impl;

import library.library.dto.GenreDto;
import library.library.mapper.GenreMapper;
import library.library.repository.GenreRepository;
import library.library.service.GenreService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class GenreServiceImpl implements GenreService {
    private final GenreRepository repository;
    private final GenreMapper mapper;

    @Override
    public List<GenreDto> getAll() {
        return mapper.toDtoList(repository.findAll());
    }
    @Override
    public GenreDto getById(Long id) {
        return mapper.toDto(repository.findById(id).orElse(null));
    }
    @Override
    public boolean add(GenreDto genreDto) {
        if (Objects.isNull(genreDto)) return false;
        repository.save(mapper.toEntity(genreDto));
        return true;
    }
    @Override
    public boolean update(Long id, GenreDto genreDto) {
        GenreDto oldGenre = getById(id);
        if (Objects.isNull(oldGenre) || Objects.isNull(genreDto)) return false;
        oldGenre.setNameDto(genreDto.getNameDto());
        repository.save(mapper.toEntity(oldGenre));
        return true;
    }
    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
