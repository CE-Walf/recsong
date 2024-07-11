package yg.recsong.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import yg.recsong.dto.BoardRequestDto;
import yg.recsong.dto.BoardResponseDto;
import yg.recsong.service.BoardService;

import java.util.List;

@RestController
@RequestMapping("/api/boards")
public class BoardApiController {

    private final BoardService boardService;

    public BoardApiController(BoardService boardService){
        this.boardService = boardService;
    }

    // [READ] -> 전체 게시판 가져오기
    @GetMapping
    public ResponseEntity<List<BoardResponseDto>> getAllBoards(){
        List<BoardResponseDto> boards = boardService.findAllBoards();
        return ResponseEntity.ok(boards);
    }

    // [READ] -> 특정 게시판 가져오기
    @GetMapping("/{id}")
    public ResponseEntity<BoardResponseDto> getBoardById(@PathVariable Long id){
        BoardResponseDto boardDto = boardService.findByBoardId(id);

        if (boardDto != null) {
            return ResponseEntity.ok(boardDto);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    // [CRAETE] -> 게시판 생성하기
    @PostMapping
    public ResponseEntity<BoardResponseDto> createBoard(@RequestBody BoardRequestDto boardRequestDto){
        BoardResponseDto createdBoard = boardService.createBoard(boardRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdBoard);
    }

    // [UPDATE] -> 특정 게시판 수정하기
    @PutMapping("/{id}")
    public ResponseEntity<BoardResponseDto> updateBoard(@PathVariable Long id, @RequestBody BoardRequestDto boardRequestDto){
        BoardResponseDto existingBoard = boardService.findByBoardId(id);

        if (existingBoard != null) {
            BoardResponseDto updatedBoard = boardService.updateBoard(id, boardRequestDto);
            return ResponseEntity.ok(updatedBoard);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // [DELETE] -> 특정 게시판 삭제하기
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBoard(@PathVariable Long id) {
        boardService.deleteBoard(id);
        return ResponseEntity.noContent().build();
    }

}
