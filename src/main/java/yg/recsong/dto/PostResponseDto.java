package yg.recsong.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostResponseDto {
    // 게시글 조회를 위한 응답 Dto이다.
    private Long id;
    private String title;
    private String content;
    private String author;
    private String createdAt;
    private Long boardId;
}
