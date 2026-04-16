package andreibri.u5_w2_d3.controllers;

import andreibri.u5_w2_d3.entities.BlogPost;
import andreibri.u5_w2_d3.payloads.BlogPostRequest;
import andreibri.u5_w2_d3.services.BlogPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/blogPosts")
public class BlogPostController {

    @Autowired
    private BlogPostService service;

    @PostMapping
    public BlogPost create(@RequestBody BlogPostRequest req) {
        return service.create(req);
    }

    @GetMapping
    public List<BlogPost> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public BlogPost getById(@PathVariable UUID id) {
        return service.getById(id);
    }

    @PutMapping("/{id}")
    public BlogPost update(@PathVariable UUID id, @RequestBody BlogPostRequest req) {
        return service.update(id, req);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable UUID id) {
        service.deleteById(id);
    }
}