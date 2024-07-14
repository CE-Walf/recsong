package yg.recsong.entity;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import yg.recsong.dto.BoardRequestDto;
import yg.recsong.dto.BoardResponseDto;

@Mapper(componentModel = "spring",  uses = {PostMapper.class})
public interface BoardMapper {

    // @Mapping 어노테이션은 MapStruct 라이브러리에서 소스 객체의 특정 필드를
    // 대상 객체의 필드에 매핑할 때 사용하는 어노테이션이다.

    @Mappings({
        @Mapping(target = "id", ignore = true), // id 필드는 매핑 X (자동생성)
        @Mapping(source = "boardRequestDto.title", target = "title"),
        @Mapping(source = "boardRequestDto.description", target = "description"),
        @Mapping(target = "posts", ignore = true) // posts는 아직 없으므로 비어있게끔
    })
    Board toEntity(BoardRequestDto boardRequestDto);

    @Mappings({
        @Mapping(source = "board.id", target = "id"),
        @Mapping(source = "board.title", target = "title"),
        @Mapping(source = "board.description", target = "description"),
        @Mapping(source = "board.posts", target = "posts")
    })
    BoardResponseDto toDto(Board board);
}
