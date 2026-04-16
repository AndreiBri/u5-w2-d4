package andreibri.u5_w2_d3.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "blog_posts")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BlogPost {

    @Id
    @GeneratedValue
    private UUID id;

    private String category;
    private String title;
    private String content;
    private int readingTime;
    private String cover;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private Author author;

}
