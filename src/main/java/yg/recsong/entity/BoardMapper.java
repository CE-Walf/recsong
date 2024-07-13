package yg.recsong.entity;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import yg.recsong.dto.BoardRequestDto;
import yg.recsong.dto.BoardResponseDto;

@Mapper(componentModel = "spring")
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
        @Mapping(source = "board.description", target = "description")
    })
    BoardResponseDto toDto(Board board);
}

// Mapstruct 라이브러리 사용하는 방식으로 수정
//@Component
//public class BoardMapper {
//    // DTO로 만드는 함수
//    public BoardDto toDto(Board board) {
//        return BoardDto.builder()
//            .id(board.getId())
//            .title(board.getTitle())
//            .description(board.getDescription())
//            .build();
//    }
//
//    // Entity로 만드는 함수
//    public Board toEntity(BoardDto boardDto) {
//        return Board.builder()
//            .title(boardDto.getTitle())
//            .description(boardDto.getDescription())
//            .build();
//    }
//}


