package andreibri.u5_w2_d3.services;

import andreibri.u5_w2_d3.entities.Author;
import andreibri.u5_w2_d3.entities.BlogPost;
import andreibri.u5_w2_d3.payloads.BlogPostRequest;
import andreibri.u5_w2_d3.repository.AuthorRepository;
import andreibri.u5_w2_d3.repository.BlogPostRepository;
import exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Service
public class BlogPostService {

    @Autowired
    private BlogPostRepository blogPostRepo;

    @Autowired
    private AuthorRepository authorRepo;

    @Autowired
    private CloudinaryService cloudinaryService;

    public BlogPost create(BlogPostRequest req, MultipartFile file) throws IOException {

        Author author = authorRepo.findById(req.getAuthorId())
                .orElseThrow(() -> new NotFoundException("Author not found"));

        String coverUrl = cloudinaryService.upload(file);

        BlogPost post = new BlogPost();
        post.setTitle(req.getTitle());
        post.setContent(req.getContent());
        post.setCategory(req.getCategory());
        post.setReadingTime(req.getReadingTime());
        post.setCover(coverUrl);
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

        Author author = authorRepo.findById(req.getAuthorId())
                .orElseThrow(() -> new NotFoundException("Autore con id " + req.getAuthorId() + " non trovato"));

        existing.setTitle(req.getTitle());
        existing.setContent(req.getContent());
        existing.setCategory(req.getCategory());
        existing.setReadingTime(req.getReadingTime());
        existing.setAuthor(author);

        return blogPostRepo.save(existing);
    }

    public void deleteById(UUID id) {
        blogPostRepo.deleteById(id);
    }
}