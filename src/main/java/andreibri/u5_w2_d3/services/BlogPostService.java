package andreibri.u5_w2_d3.services;

import andreibri.u5_w2_d3.entities.Author;
import andreibri.u5_w2_d3.entities.BlogPost;
import andreibri.u5_w2_d3.payloads.BlogPostRequest;
import andreibri.u5_w2_d3.repository.AuthorRepository;
import andreibri.u5_w2_d3.repository.BlogPostRepository;
import exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class BlogPostService {

    @Autowired
    private BlogPostRepository blogPostRepo;

    @Autowired
    private AuthorRepository authorRepo;

    public BlogPost create(BlogPostRequest req) {

        Author author = authorRepo.findById(req.authorId)
                .orElseThrow(() -> new RuntimeException("Author not found"));

        BlogPost post = new BlogPost();
        post.setCategory(req.category);
        post.setTitle(req.title);
        post.setContent(req.content);
        post.setReadingTime(req.readingTime);
        post.setCover("https://picsum.photos/200/300");
        post.setAuthor(author);

        return blogPostRepo.save(post);
    }

    public List<BlogPost> getAll() {
        return blogPostRepo.findAll();
    }

    public BlogPost getById(UUID id) {
        return blogPostRepo.findById(id)
                .orElseThrow(() -> new NotFoundException("BlogPost con id " + id + " non trovato"));
    }

    public BlogPost update(UUID id, BlogPostRequest req) {
        BlogPost existing = this.getById(id);
        Author author = authorRepo.findById(req.authorId)
                .orElseThrow(() -> new NotFoundException("Autore con id " + req.authorId + " non trovato"));
        existing.setCategory(req.category);
        existing.setTitle(req.title);
        existing.setContent(req.content);
        existing.setReadingTime(req.readingTime);
        existing.setAuthor(author);
        return blogPostRepo.save(existing);
    }

    public void deleteById(UUID id) {
        blogPostRepo.deleteById(id);
    }
}
