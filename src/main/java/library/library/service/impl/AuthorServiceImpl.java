package library.library.service.impl;

import library.library.dto.AuthorDto;
import library.library.mapper.AuthorMapper;
import library.library.model.Author;
import library.library.repository.AuthorRepository;
import library.library.service.AuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository repository;
    private final AuthorMapper mapper;

    @Override
    public List<AuthorDto> getAll() {
        return mapper.toDtoList(repository.findAll());
    }

    @Override
    public AuthorDto getById(Long id) {
        return mapper.toDto(repository.findById(id).orElse(null));
    }

    @Override
    public boolean add(AuthorDto authorDto) {
        if (Objects.isNull(authorDto)) return false;
        repository.save(mapper.toEntity(authorDto));
        return true;
    }

    @Override
    public boolean update(Long id, AuthorDto authorDto) {
        Author author = repository.findById(id).orElse(null);
        if (author == null || authorDto == null) return false;

        author.setName(authorDto.getNameDto());
        repository.save(author);
        return true;
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
