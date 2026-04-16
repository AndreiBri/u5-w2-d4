package andreibri.u5_w2_d3.controllers;

import andreibri.u5_w2_d3.entities.Author;
import andreibri.u5_w2_d3.payloads.AuthorRequest;
import andreibri.u5_w2_d3.services.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/authors")
public class AuthorController {

    @Autowired
    private AuthorService service;

    @PostMapping
    public Author create(@RequestBody AuthorRequest req) {
        return service.create(req);
    }

    @GetMapping
    public List<Author> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public Author getById(@PathVariable UUID id) {
        return service.getById(id);
    }

    @PutMapping("/{id}")
    public Author update(@PathVariable UUID id, @RequestBody AuthorRequest req) {
        return service.update(id, req);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable UUID id) {
        service.delete(id);
    }
}
