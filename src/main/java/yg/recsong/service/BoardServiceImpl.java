package yg.recsong.service;

import jakarta.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import yg.recsong.dto.BoardRequestDto;
import yg.recsong.dto.BoardResponseDto;
import yg.recsong.entity.Board;
import yg.recsong.entity.BoardMapper;
import yg.recsong.repository.BoardRepository;

@Service
@Transactional
public class BoardServiceImpl implements BoardService {
    private final BoardRepository boardRepository;
    private final BoardMapper boardMapper;

    public BoardServiceImpl(BoardRepository boardRepository, BoardMapper boardMapper) {
        this.boardRepository = boardRepository;
        this.boardMapper = boardMapper;
    }

    // 모든 게시글 불러오기
    @Override
    public List<BoardResponseDto> findAllBoards() {
        List<Board> boards = boardRepository.findAll();
        return boards.stream()
            .map(boardMapper::toDto)
            .collect(Collectors.toList());
    }

    // ID로 해당 게시판 불러오기
    @Override
    public BoardResponseDto findByBoardId(Long id) {
        Board board = boardRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("게시판을 찾을 수 없습니다."));
        return boardMapper.toDto(board);
    }

    // 게시판 생성하기
    @Override
    public BoardResponseDto createBoard(BoardRequestDto boardRequestDto) {
        Board board = boardMapper.toEntity(boardRequestDto);
        Board savedBoard = boardRepository.save(board);
        return boardMapper.toDto(savedBoard);
    }

    // 게시판 수정하기
    @Override
    public BoardResponseDto updateBoard(Long id, BoardRequestDto boardRequestDto){
        Board board = boardRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("게시판을 찾을 수 없습니다."));
        board.setTitle(boardRequestDto.getTitle());
        board.setDescription(boardRequestDto.getDescription());
        Board updatedBoard = boardRepository.save(board);
        return boardMapper.toDto(updatedBoard);
    }
    
    // 게시판 삭제하기
    @Override
    public void deleteBoard(Long id) {
        boardRepository.deleteById(id);
    }


    // 게시판 이름 가져오기
    public String findBoardNameById(Long id) {
        Board board = boardRepository.findById(id).orElse(null);
        if (board != null) {
            return board.getTitle();
        } else {
            throw new RuntimeException("Board not found with id: " + id);
        }
    }

}
