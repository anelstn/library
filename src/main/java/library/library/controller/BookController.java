package library.library.controller;

import library.library.dto.BookDto;
import library.library.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/book")
@RequiredArgsConstructor
public class BookController {
    private final BookService service;

    @GetMapping
    public ResponseEntity<List<BookDto>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        BookDto dto = service.getById(id);
        if (dto == null) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Book not found");
        return ResponseEntity.ok(dto);
    }

    @PostMapping
    public ResponseEntity<?> add(@RequestBody BookDto dto) {
        boolean created = service.add(dto);
        if (!created) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid data");
        return ResponseEntity.status(HttpStatus.CREATED).body("Book created successfully");
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody BookDto dto) {
        boolean updated = service.update(id, dto);
        if (!updated) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Update failed");
        return ResponseEntity.ok("Book updated successfully");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
