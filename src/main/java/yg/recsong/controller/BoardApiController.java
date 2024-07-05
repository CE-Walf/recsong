package yg.recsong.controller;

import java.util.Optional;
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

import yg.recsong.dto.BoardDto;
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
    public ResponseEntity<List<BoardDto>> getAllBoards(){
        List<BoardDto> boards = boardService.findAllBoards();
        return ResponseEntity.ok(boards);
    }

    // [READ] -> 특정 게시판 가져오기
    @GetMapping("/{id}")
    public ResponseEntity<BoardDto> getBoardById(@PathVariable Long id){
        BoardDto boardDto = boardService.findByBoardId(id);

        if (boardDto != null) {
            return ResponseEntity.ok(boardDto);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    // [CRAETE] -> 게시판 생성하기
    @PostMapping
    public ResponseEntity<BoardDto> createBoard(@RequestBody BoardDto boardDto){
        BoardDto createdBoard = boardService.createBoard(boardDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdBoard);
    }

    // [UPDATE] -> 특정 게시판 수정하기
    @PutMapping("/{id}")
    public ResponseEntity<BoardDto> updateBoard(@PathVariable Long id, @RequestBody BoardDto boardDto){
        BoardDto existingBoard = boardService.findByBoardId(id);

        if (existingBoard != null) {
            boardDto.setId(id);
            boardService.updateBoard(id, boardDto);
            return ResponseEntity.ok(boardDto);
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
