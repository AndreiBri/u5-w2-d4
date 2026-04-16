package andreibri.u5_w2_d3.repository;

import andreibri.u5_w2_d3.entities.BlogPost;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface BlogPostRepository extends JpaRepository<BlogPost, UUID> {
}
