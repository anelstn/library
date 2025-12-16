package library.library.service;

import library.library.dto.AuthorDto;
import java.util.List;

public interface AuthorService {
    List<AuthorDto> getAll();
    AuthorDto getById(Long id);
    boolean add(AuthorDto authorDto);
    boolean update(Long id, AuthorDto authorDto);
    void delete(Long id);
}
