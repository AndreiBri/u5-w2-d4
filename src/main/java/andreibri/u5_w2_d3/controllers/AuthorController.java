package andreibri.u5_w2_d3.controllers;

import andreibri.u5_w2_d3.entities.Author;
import andreibri.u5_w2_d3.payloads.AuthorRequest;
import andreibri.u5_w2_d3.services.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/authors")
public class AuthorController {

    @Autowired
    private AuthorService service;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Author create(
            @RequestPart("data") AuthorRequest req,
            @RequestPart("avatar") MultipartFile file) throws IOException {

        return service.create(req, file);
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
