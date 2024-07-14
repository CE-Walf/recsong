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
