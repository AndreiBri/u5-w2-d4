package andreibri.u5_w2_d3.repository;

import andreibri.u5_w2_d3.entities.Author;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AuthorRepository extends JpaRepository<Author, UUID> {
}
