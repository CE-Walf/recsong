package yg.recsong.entity;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import yg.recsong.dto.PostCreateDto;
import yg.recsong.dto.PostResponseDto;

@Mapper(componentModel = "spring")
public interface PostMapper {

    @Mappings({
        @Mapping(target = "id", ignore=true),
        @Mapping(source = "postCreateDto.title", target="title"),
        @Mapping(source = "postCreateDto.content", target = "content"),
        @Mapping(source = "postCreateDto.author", target = "author"),
        @Mapping(target = "comments", ignore=true),
    })
    Post toEntity(PostCreateDto postCreateDto, Board board);

    @Mappings({
        @Mapping(target = "id", source = "post.id"),
        @Mapping(target = "title", source = "post.title"),
        @Mapping(target = "content", source = "post.content"),
        @Mapping(target = "author", source = "post.author"),
        @Mapping(target = "createdAt", source = "post.createdAt"),
        @Mapping(target = "boardId", source = "post.board.id")
    })
    PostResponseDto toDto(Post post);

}

// Mapstruct 라이브러리 사용하는 방식으로 수정
// Mapper 클래스, @Component를 이용하여 Bean 등록 해주자.
//@Component
//public class PostMapper {
//    public Post toEntity(PostCreateDto postCreateDto, Board board) {
//        return Post.builder()
//            .title(postCreateDto.getTitle())
//            .content(postCreateDto.getContent())
//            .author(postCreateDto.getAuthor())
//            .board(board)
//            .build();
//    }
//
//    public PostResponseDto toDto(Post post) {
//        return PostResponseDto.builder()
//            .id(post.getId())
//            .title(post.getTitle())
//            .content(post.getContent())
//            .author(post.getAuthor())
//            .createdAt(post.getCreatedAt())
//            .boardId(post.getBoard().getId())
//            .build();
//    }
//}
