package shop.mtcoding.blog.board;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardPaging extends JpaRepository<Board, Long> {
    Page<Board> findAll(Pageable pageable);
}
