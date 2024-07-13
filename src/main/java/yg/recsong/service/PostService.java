package yg.recsong.service;

import java.util.List;
import yg.recsong.dto.PostCreateDto;
import yg.recsong.dto.PostResponseDto;

public interface PostService {

    List<PostResponseDto> findAllPosts();     // 전체 게시글 조회

    List<PostResponseDto> findAllPostsByBoardId(Long boardId); // 게시판 ID를 통해 게시글 조회하기

    PostResponseDto findByPostId(Long postId);     // 게시글 조회

    PostResponseDto createPost(PostCreateDto postCreateDto); // 게시글 생성하기

    PostResponseDto updatePost(Long postId, PostCreateDto updatedPost); // 게시글 수정

    void deletePost(Long postId); // 게시글 삭제
}
