package andreibri.u5_w2_d3.services;

import andreibri.u5_w2_d3.entities.Author;
import andreibri.u5_w2_d3.payloads.AuthorRequest;
import andreibri.u5_w2_d3.repository.AuthorRepository;
import exceptions.NotFoundException;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Service
public class AuthorService {

    @Autowired
    private AuthorRepository authorRepo;


    public Author create(AuthorRequest req) {
        Author a = new Author();
        a.setFirstName(req.firstName);
        a.setLastName(req.lastName);
        a.setEmail(req.email);
        a.setBirthDate(req.birthDate);
        a.setAvatar("https://ui-avatars.com/api/?name="
                + req.firstName + "+" + req.lastName);

        return authorRepo.save(a);

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
        existing.setFirstName(req.firstName);
        existing.setLastName(req.lastName);
        existing.setEmail(req.email);
        existing.setBirthDate(req.birthDate);
        existing.setAvatar("https://ui-avatars.com/api/?name=" + req.firstName + "+" + req.lastName);
        return authorRepo.save(existing);
    }

    public void delete(UUID id) {
        authorRepo.deleteById(id);
    }

}
