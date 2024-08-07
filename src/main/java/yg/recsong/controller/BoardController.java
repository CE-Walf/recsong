package yg.recsong.controller;

import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;
import yg.recsong.dto.BoardRequestDto;
import yg.recsong.dto.BoardResponseDto;
import yg.recsong.dto.PostResponseDto;
import yg.recsong.service.BoardService;

@Controller
public class BoardController {

    private final BoardService boardService;

    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    // [기본 페이지 설정]
    // localhost:8080으로 접근하게 되면, 게시판을 보여주는 /boards로 넘어가게 한다.
    @GetMapping("/")
    public String redirectToHome() {
        return "redirect:/boards";
    }

    // [메인 페이지]
    // 현재 존재하는 게시판들을 보여준다.
    @GetMapping("/boards")
    public String showBoardList(Model model) {
        model.addAttribute("boards", boardService.findAllBoards());
        return "board/boards";
    }

    // [게시판 생성하는 사이트로 이동]
    @GetMapping("/boards/new")
    public String showCreateBoard(Model model){
        model.addAttribute("boardDto", new BoardRequestDto());
        return "board/createBoard";
    }

    // [게시판 생성]
    // 게시판이 생성된 후, 게시판 목록이 있는 곳으로 이동하도록 한다.
    // RedirectAttributes를 통해, 리다이렉트 시 데이터를 전달.
    @PostMapping("/boards/new")
    public RedirectView createBoard(@ModelAttribute BoardRequestDto boardRequestDto, RedirectAttributes redirectAttributes){
        // 게시판 생성 로직 수행
        boardService.createBoard(boardRequestDto);
        // 성공 메시지 추가
        redirectAttributes.addFlashAttribute("message", "게시판이 생성되었습니다.");
        // 게시판 목록 페이지로 리다이렉션
        return new RedirectView("/boards");
    }

    // [게시판 수정 사이트로 이동]
    @GetMapping("/boards/edit/{id}")
    public String showUpdateBoard(@PathVariable Long id, Model model){
        BoardResponseDto boardDto = boardService.findByBoardId(id);
        model.addAttribute("boardDto", boardDto);
        return "board/modifyBoard";
    }

    // [게시판 상세정보 사이트로 이동]
    @GetMapping("/boards/{id}")
    public String showPostsOfBoard(@PathVariable Long id, Model model){
        // 게시판 이름 가져오기
        String boardName = boardService.findBoardNameById(id);
        // 게시글 가져오기
        List<PostResponseDto> posts = boardService.findByBoardId(id).getPosts();

        // 뷰에서 게시판의 이름과, 게시글을 띄워야하므로 boardName과 Post를 attribute해준다.
        model.addAttribute("boardName", boardName);
        model.addAttribute("posts", posts);
        model.addAttribute("boardId", id); // 게시글 이동을 위해서

        return "board/posts";
    }

    // [게시판 이름, 설명 수정]
    @PutMapping("/boards/edit/{id}")
    public RedirectView updateBoard(@PathVariable Long id, @ModelAttribute BoardRequestDto boardDto, RedirectAttributes redirectAttributes){
        // 게시판 수정 로직 수행
        boardService.updateBoard(id, boardDto);
        // 성공 메시지 추가
        redirectAttributes.addFlashAttribute("successMessage", "게시글이 성공적으로 수정되었습니다.");
        // 게시판 목록 페이지로 리다이렉션
        return new RedirectView("/boards");
    }

    // [게시글 삭제]
    @DeleteMapping("/boards/delete/{id}")
    public RedirectView deleteBoard(@PathVariable Long id){
        boardService.deleteBoard(id);
        return new RedirectView("/boards");
    }

}
