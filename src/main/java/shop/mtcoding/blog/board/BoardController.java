package shop.mtcoding.blog.board;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@RequiredArgsConstructor
@Controller
public class BoardController {
    private final BoardRepository boardRepository;
    private final BoardPaging boardPaging;
    @GetMapping("/")
    public String index(Model model, @PageableDefault(size = 5, sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {


        Page<Board> boardPage = boardPaging.findAll(pageable);
        model.addAttribute("boardList", boardPage);
        model.addAttribute("previous", pageable.previousOrFirst().getPageNumber());
        model.addAttribute("next", pageable.next().getPageNumber());
        model.addAttribute("hasPrevious", boardPage.hasPrevious());
        model.addAttribute("hasNext" , boardPage.hasNext());


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
