package yg.recsong.service;

import java.util.List;
import yg.recsong.dto.BoardDto;

public interface BoardService {
    List<BoardDto> findAllBoards();
    BoardDto findByBoardId(Long id);
    BoardDto createBoard(BoardDto boardDto);
    BoardDto updateBoard(Long id, BoardDto boardDTO);
    void deleteBoard(Long id);
}
