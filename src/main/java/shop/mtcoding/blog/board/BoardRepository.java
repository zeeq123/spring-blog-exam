package shop.mtcoding.blog.board;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Repository
public class BoardRepository {
    private final EntityManager em;

    @Transactional
    public void delete(int id){
        Query query = em.createNativeQuery("delete from board_tb where id=?");
        query.setParameter(1, id);
        query.executeUpdate();
    }

    @Transactional
    public void update(BoardRequest.BoardDTO requestDTO, int id){
        Query query = em.createNativeQuery("update board_tb set title=?, content=?, author=? where id=?");
        query.setParameter(1, requestDTO.getTitle());
        query.setParameter(2, requestDTO.getContent());
        query.setParameter(3, requestDTO.getAuthor());
        query.setParameter(4, id);
        query.executeUpdate();
    }

    @Transactional
    public void save(BoardRequest.BoardDTO requestDTO){
        Query query = em.createNativeQuery("insert into board_tb(title, content, author) values (?, ?, ?)");
        query.setParameter(1, requestDTO.getTitle());
        query.setParameter(2, requestDTO.getContent());
        query.setParameter(3, requestDTO.getAuthor());
        query.executeUpdate();
    }

    public List<Board> findAll(){
        Query query = em.createNativeQuery("select * from board_tb order by id desc", Board.class);

        return query.getResultList();
    }
}
