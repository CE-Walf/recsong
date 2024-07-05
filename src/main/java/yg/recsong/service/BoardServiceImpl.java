package yg.recsong.service;

import jakarta.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import yg.recsong.dto.BoardDto;
import yg.recsong.entity.Board;
import yg.recsong.entity.BoardMapper;
import yg.recsong.repository.BoardRepository;

@Service
@Transactional
public class BoardServiceImpl implements BoardService {
    private final BoardRepository boardRepository;

    public BoardServiceImpl(BoardRepository boardRepository){
        this.boardRepository = boardRepository;
    }

    // 모든 게시글 불러오기
    @Override
    public List<BoardDto> findAllBoards() {
        List<Board> boards = boardRepository.findAll();
        return boards.stream()
            .map(BoardMapper::toDto)
            .collect(Collectors.toList());
    }

    // ID로 해당 게시판 불러오기
    @Override
    public BoardDto findByBoardId(Long id) {
        Board board = boardRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("게시판을 찾을 수 없습니다."));
        return BoardMapper.toDto(board);
    }

    // 게시판 생성하기
    @Override
    public BoardDto createBoard(BoardDto boardDto) {
        Board board = BoardMapper.toEntity(boardDto);
        Board savedBoard = boardRepository.save(board);
        return BoardMapper.toDto(savedBoard);
    }

    // 게시판 수정하기
    @Override
    public BoardDto updateBoard(Long id, BoardDto boardDto){
        Board board = boardRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("게시판을 찾을 수 없습니다."));
        board.setTitle(boardDto.getTitle());
        board.setDescription(boardDto.getDescription());
        Board updatedBoard = boardRepository.save(board);
        return BoardMapper.toDto(updatedBoard);
    }
    
    // 게시판 삭제하기
    @Override
    public void deleteBoard(Long id) {
        boardRepository.deleteById(id);
    }


}
