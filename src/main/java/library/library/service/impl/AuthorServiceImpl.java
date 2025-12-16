package library.library.service.impl;

import library.library.model.Author;
import library.library.repository.AuthorRepository;
import library.library.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorServiceImpl implements AuthorService {

    @Autowired
    private AuthorRepository authorRepository;

    @Override
    public List<Author> getAll() {
        return authorRepository.findAll();
    }

    @Override
    public Author getById(Long id) {
        return authorRepository.findById(id).orElse(null);
    }

    @Override
    public void add(Author author) {
        authorRepository.save(author);
    }

    @Override
    public void update(Long id, Author author) {
        Author existing = authorRepository.findById(id).orElse(null);
        if (existing != null) {
            existing.setName(author.getName());
            authorRepository.save(existing);
        }
    }

    @Override
    public void delete(Long id) {
        authorRepository.deleteById(id);
    }
}
