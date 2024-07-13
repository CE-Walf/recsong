package yg.recsong.service;

import jakarta.transaction.Transactional;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import yg.recsong.dto.PostCreateDto;
import yg.recsong.dto.PostResponseDto;
import yg.recsong.entity.Board;
import yg.recsong.entity.Post;
import yg.recsong.entity.PostMapper;
import yg.recsong.repository.BoardRepository;
import yg.recsong.repository.PostRepository;

@Service
@Transactional
public class PostServiceImpl implements PostService{

    private final PostRepository postRepository;
    private final BoardRepository boardRepository;
    private final PostMapper postMapper;

    public PostServiceImpl(PostRepository postRepository, BoardRepository boardRepository, PostMapper postMapper){
        this.postRepository = postRepository;
        this.boardRepository = boardRepository;
        this.postMapper = postMapper;
    }

    // 게시판과 관련 없이 전체 게시글을 찾는다. (실제 사용 X)
    public List<PostResponseDto> findAllPosts(){
        List<Post> posts = postRepository.findAll();
        return posts.stream()
            .map(postMapper::toDto)
            .collect(Collectors.toList());
    }

    // 게시판 ID를 이용하여, 해당 게시판의 전체 게시글을 찾는다.
    public List<PostResponseDto> findAllPostsByBoardId(Long boardId) {
        List<Post> posts = postRepository.findByBoardId(boardId);
        return posts.stream()
            .map(postMapper::toDto)
            .collect(Collectors.toList());
    }

    // 해당 게시글의 ID로 게시글의 내용을 조회한다.
    public PostResponseDto findByPostId(Long postId){
        Post post = postRepository.findById(postId)
            .orElseThrow(() -> new NoSuchElementException("이 PostId는 찾을 수 없습니다.: " + postId));
        return postMapper.toDto(post);
    }

    // 게시글을 만든다.
    public PostResponseDto createPost(PostCreateDto postCreateDto){
        // 먼저 게시글을 만들 게시판의 정보를 조회한다.
        Board board = boardRepository.findById(postCreateDto.getBoardId()).orElseThrow(() -> new IllegalArgumentException("유효하지 않은 게시판 ID"));

        // 응답 받은 게시글을 저장한다.
        Post post = postMapper.toEntity(postCreateDto, board);
        Post savedPost = postRepository.save(post);

        return postMapper.toDto(savedPost);
    }

    // 게시글을 수정한다.
    @Override
    public PostResponseDto updatePost(Long postId, PostCreateDto updatedPost) {
        Post post = postRepository.findById(postId)
            .orElseThrow(() -> new NoSuchElementException("No post found with id: " + postId));

        // 게시글의 내용을 업데이트
        post.setTitle(updatedPost.getTitle());
        post.setContent(updatedPost.getContent());

        // 변경된 게시글을 저장
        postRepository.save(post);

        return postMapper.toDto(post);
    }

    @Override
    public void deletePost(Long postId) {
        if (!postRepository.existsById(postId)) {
            throw new NoSuchElementException("No post found with id: " + postId);
        }
        postRepository.deleteById(postId);
    }



}
