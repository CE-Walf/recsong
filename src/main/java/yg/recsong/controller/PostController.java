package yg.recsong.controller;


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
import yg.recsong.dto.PostCreateDto;
import yg.recsong.dto.PostResponseDto;
import yg.recsong.service.BoardService;
import yg.recsong.service.PostService;

@Controller
public class PostController {
    private final PostService postService;
    private final BoardService boardService;

    public PostController(PostService postService, BoardService boardService){
        this.postService = postService;
        this.boardService = boardService;
    }

    // [게시글 상세 페이지 이동]
    @GetMapping("/boards/{boardId}/posts/{postId}")
    public String showPost (@PathVariable Long boardId, @PathVariable Long postId, Model model){
        PostResponseDto post = postService.findByPostId(postId);

        // 모델에 게시글 내용 추가
        model.addAttribute("post", post);

        return "post/post";
    }

    // [게시글 생성 페이지로의 이동]
    @GetMapping("/boards/{boardId}/posts/new")
    public String showCreatePostForm(@PathVariable Long boardId, Model model){
        // 게시판 이름 가져오기
        String boardName = boardService.findBoardNameById(boardId);

        // model을 통해 View에 넘기기
        model.addAttribute("boardName", boardName);
        model.addAttribute("boardId", boardId);
        model.addAttribute("post", new PostCreateDto());

        return "post/createPost";
    }

    // [게시글 생성]
    @PostMapping("/boards/{boardId}/posts")
    public RedirectView createPost(@PathVariable Long boardId, @ModelAttribute("post") PostCreateDto postCreateDto, RedirectAttributes redirectAttributes){
        // 게시글을 생성해준다.
        postService.createPost(postCreateDto);

        // 성공 메시지 추가
        redirectAttributes.addFlashAttribute("successMessage", "게시판이 성공적으로 생성되었습니다.");

        // 생성하고 해당 게시판의 전체 게시글을 보는 페이지로 이동
        return new RedirectView(String.format("/boards/%d", boardId));
    }

    // [게시글 수정 페이지로 이동]
    @GetMapping("/boards/{boardId}/posts/{postId}/edit")
    public String showUpdatePostForm(@PathVariable Long boardId, @PathVariable Long postId, Model model){
        // 수정할 게시글의 데이터를 가져온다.
        PostResponseDto post = postService.findByPostId(postId);

        // 수정할 게시글이 담긴 게시판의 이름을 가져온다.
        String boardName = boardService.findBoardNameById(boardId);

        // 이제 이 정보들을 View로 띄우기 위해 가져온다.
        model.addAttribute("boardName", boardName);
        model.addAttribute("post", post);
        model.addAttribute("boardId", boardId);

        return "post/editPost";
    }

    // [게시글 수정]
    @PutMapping("/boards/{boardId}/posts/{postId}")
    public RedirectView updatePost(@PathVariable Long boardId, @PathVariable Long postId, @ModelAttribute("post") PostCreateDto postCreateDto, RedirectAttributes redirectAttributes){

        // 게시글 수정
        postService.updatePost(postId, postCreateDto);

        // 성공 메시지 추가
        redirectAttributes.addFlashAttribute("successMessage", "게시판이 성공적으로 수정되었습니다.");

        // 수정 완료 후 게시글 상세보기 페이지로 리다이렉트.
        return new RedirectView(String.format("/boards/%d/posts/%d", boardId, postId));
    }


    // [게시글 삭제]
    @DeleteMapping("/boards/{boardId}/posts/{postId}")
    public RedirectView deletePost(@PathVariable Long boardId, @PathVariable Long postId, RedirectAttributes redirectAttributes){
        // 해당 게시글 삭제하기.
        postService.deletePost(postId);

        // 성공 메시지 추가
        redirectAttributes.addFlashAttribute("successMessage", "게시글이 성공적으로 삭제되었습니다.");

        return new RedirectView(String.format("/boards/%d", boardId));
    }

}
