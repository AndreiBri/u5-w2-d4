package andreibri.u5_w2_d3.services;

import andreibri.u5_w2_d3.entities.Author;
import andreibri.u5_w2_d3.payloads.AuthorRequest;
import andreibri.u5_w2_d3.repository.AuthorRepository;
import exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Service
public class AuthorService {

    @Autowired
    private AuthorRepository authorRepo;

    @Autowired
    private CloudinaryService cloudinaryService;

    public Author create(AuthorRequest req, MultipartFile file) throws IOException {

        String avatarUrl = cloudinaryService.upload(file);

        Author author = new Author();
        author.setFirstName(req.getFirstName());
        author.setLastName(req.getLastName());
        author.setEmail(req.getEmail());
        author.setBirthDate(req.getBirthDate());
        author.setAvatar(avatarUrl);

        return authorRepo.save(author);
    }

    public List<Author> getAll() {
        return authorRepo.findAll();
    }

    public Author getById(UUID id) {
        return authorRepo.findById(id)
                .orElseThrow(() -> new NotFoundException("Autore con id " + id + " non trovato"));
    }

    public Author update(UUID id, AuthorRequest req) {
        Author existing = this.getById(id);

        existing.setFirstName(req.getFirstName());
        existing.setLastName(req.getLastName());
        existing.setEmail(req.getEmail());
        existing.setBirthDate(req.getBirthDate());
        existing.setAvatar("https://ui-avatars.com/api/?name="
                + req.getFirstName() + "+" + req.getLastName());

        return authorRepo.save(existing);
    }

    public void delete(UUID id) {
        authorRepo.deleteById(id);
    }

    public Author uploadAvatar(UUID id, MultipartFile file) throws IOException {

        Author author = this.getById(id);

        String avatarUrl = cloudinaryService.upload(file);

        author.setAvatar(avatarUrl);

        System.out.println(author);
        return authorRepo.save(author);

    }
}