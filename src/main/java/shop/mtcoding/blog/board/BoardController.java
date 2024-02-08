package shop.mtcoding.blog.board;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class BoardController {
    private final BoardRepository boardRepository;
    private final HttpSession session;

    @GetMapping("/")
    public String index(HttpServletRequest request) {
        List<Board> boardList = boardRepository.findAll();

        request.setAttribute("boardList", boardList);

        return "index";
    }

    @GetMapping("/board/saveForm")
    public String saveForm() {
        return "board/saveForm";
    }

    @GetMapping("/board/{id}/updateForm")
    public String updateForm(@PathVariable int id) {
        return "board/updateForm";
    }

    @PostMapping("/board/save")
    public String save(BoardRequest.BoardDTO requestDTO, HttpServletRequest request){
        if (requestDTO.getTitle().length() >= 20 || requestDTO.getContent().length() >= 20){
            request.setAttribute("status", 400);
            request.setAttribute("msg" ,"title 혹은 content 글자 수가 20자 이하이여야합니다.");
            return "error/40x";
        }


        boardRepository.save(requestDTO);

        return "redirect:/";
    }

    @PostMapping("/board/{id}/update")
    public String update(@PathVariable int id, BoardRequest.BoardDTO requestDTO){
        boardRepository.update(requestDTO, id);

        return "redirect:/";
    }

    @PostMapping("/board/{id}/delete")
    public String delete(@PathVariable int id){
        boardRepository.delete(id);

        return "redirect:/";
    }
}
