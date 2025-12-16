package library.library.service.impl;

import library.library.dto.BookDto;
import library.library.mapper.BookMapper;
import library.library.repository.BookRepository;
import library.library.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {
    private final BookRepository repository;
    private final BookMapper mapper;

    @Override
    public List<BookDto> getAll() {
        return mapper.toDtoList(repository.findAll());
    }
    @Override
    public BookDto getById(Long id) {
        return mapper.toDto(repository.findById(id).orElse(null));
    }
    @Override
    public boolean add(BookDto bookDto) {
        if (Objects.isNull(bookDto)) return false;
        repository.save(mapper.toEntity(bookDto));
        return true;
    }
    @Override
    public boolean update(Long id, BookDto bookDto) {
        BookDto oldBook = getById(id);
        if (Objects.isNull(oldBook) || Objects.isNull(bookDto)) return false;
        oldBook.setTitleDto(bookDto.getTitleDto());
        oldBook.setPagesDto(bookDto.getPagesDto());
        oldBook.setYearDto(bookDto.getYearDto());
        oldBook.setAuthorDto(bookDto.getAuthorDto());
        oldBook.setGenresDto(bookDto.getGenresDto());
        repository.save(mapper.toEntity(oldBook));
        return true;
    }
    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
