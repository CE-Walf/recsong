package yg.recsong.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PostCreateDto {
    // 게시글 생성을 위한 요청 Dto이다.
    private String title;
    private String content;
    private String author;
    private Long boardId;
}
