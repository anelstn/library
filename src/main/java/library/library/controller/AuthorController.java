package library.library.controller;

import library.library.dto.AuthorDto;
import library.library.service.AuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/author")
@RequiredArgsConstructor
public class AuthorController {
    private final AuthorService service;

    @GetMapping
    public ResponseEntity<List<AuthorDto>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        AuthorDto dto = service.getById(id);
        if (dto == null) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Author not found");
        return ResponseEntity.ok(dto);
    }

    @PostMapping
    public ResponseEntity<?> add(@RequestBody AuthorDto dto) {
        boolean created = service.add(dto);
        if (!created) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid data");
        return ResponseEntity.status(HttpStatus.CREATED).body("Author created successfully");
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody AuthorDto dto) {
        boolean updated = service.update(id, dto);
        if (!updated) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Update failed");
        return ResponseEntity.ok("Author updated successfully");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
