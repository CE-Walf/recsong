package yg.recsong.service;

import java.util.List;
import yg.recsong.dto.BoardRequestDto;
import yg.recsong.dto.BoardResponseDto;

public interface BoardService {

    List<BoardResponseDto> findAllBoards();

    BoardResponseDto findByBoardId(Long id);

    BoardResponseDto createBoard(BoardRequestDto boardDto);

    BoardResponseDto updateBoard(Long id, BoardRequestDto boardDTO);

    void deleteBoard(Long id);

    String findBoardNameById(Long id);
}
