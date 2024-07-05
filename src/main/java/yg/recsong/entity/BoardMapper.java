package yg.recsong.entity;

import yg.recsong.dto.BoardDto;

public class BoardMapper {
    // DTO로 만드는 함수
    public static BoardDto toDto(Board board) {
        return BoardDto.builder()
            .id(board.getId())
            .title(board.getTitle())
            .description(board.getDescription())
            .build();
    }

    // Entity로 만드는 함수
    public static Board toEntity(BoardDto boardDto) {
        return Board.builder()
            .title(boardDto.getTitle())
            .description(boardDto.getDescription())
            .build();
    }
}
