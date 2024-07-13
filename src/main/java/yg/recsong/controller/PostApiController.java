package yg.recsong.controller;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import yg.recsong.dto.PostCreateDto;
import yg.recsong.dto.PostResponseDto;
import yg.recsong.service.PostService;
@RestController
@RequestMapping("/api/posts")
public class PostApiController {
    public final PostService postService;

    public PostApiController(PostService postService){
        this.postService = postService;
    }

    // [전체 게시글 다 조회]
    @GetMapping
    public ResponseEntity<List<PostResponseDto>> getAllPosts() {
        List<PostResponseDto> posts = postService.findAllPosts();
        return ResponseEntity.ok(posts);
    }

    // [게시판 ID로 게시글 조회]
    @GetMapping("/board/{boardId}")
    public ResponseEntity<List<PostResponseDto>> getPostsByBoardId(@PathVariable Long boardId) {
        List<PostResponseDto> posts = postService.findAllPostsByBoardId(boardId);
        return ResponseEntity.ok(posts);
    }

    // [특정 게시글 조회]
    @GetMapping("/{postId}")
    public ResponseEntity<PostResponseDto> getPostById(@PathVariable Long postId){
        PostResponseDto post = postService.findByPostId(postId);
        return ResponseEntity.ok(post);
    }

    // [특정 게시글 생성]
    @PostMapping
    public ResponseEntity<PostResponseDto> createPost(@RequestBody PostCreateDto postCreateDto){
        PostResponseDto createdPost = postService.createPost(postCreateDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdPost);
    }

    // [특정 게시글 수정]
    @PutMapping("/{postId}")
    public ResponseEntity<PostResponseDto> updatePost(@PathVariable Long postId, @RequestBody PostCreateDto postCreateDto) {
        PostResponseDto updatedPost = postService.updatePost(postId, postCreateDto);
        return ResponseEntity.ok(updatedPost);
    }

    @DeleteMapping("/{postId}")
    public ResponseEntity<Void> deletePost(@PathVariable Long postId) {
        postService.deletePost(postId);
        return ResponseEntity.noContent().build();
    }

}
