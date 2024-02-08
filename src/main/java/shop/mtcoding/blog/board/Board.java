package shop.mtcoding.blog.board;

import jakarta.persistence.*;
import lombok.Data;

@Table(name="board_tb")
@Data
@Entity
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(length = 20)
    private String title;
    @Column(length = 20)
    private String content;
    private String author;

}
