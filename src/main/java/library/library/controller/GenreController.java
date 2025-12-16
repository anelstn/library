package library.library.controller;

import library.library.dto.GenreDto;
import library.library.service.GenreService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/genre")
@RequiredArgsConstructor
public class GenreController {
    private final GenreService service;

    @GetMapping
    public ResponseEntity<List<GenreDto>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        GenreDto dto = service.getById(id);
        if (dto == null) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Genre not found");
        return ResponseEntity.ok(dto);
    }

    @PostMapping
    public ResponseEntity<?> add(@RequestBody GenreDto dto) {
        boolean created = service.add(dto);
        if (!created) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid data");
        return ResponseEntity.status(HttpStatus.CREATED).body("Genre created successfully");
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody GenreDto dto) {
        boolean updated = service.update(id, dto);
        if (!updated) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Update failed");
        return ResponseEntity.ok("Genre updated successfully");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
