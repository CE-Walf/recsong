package yg.recsong.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BoardDto {
    // DTO는 계층 간 데이터 전송을 위해 사용되는 객체인데,
    // ID는 해당 객체를 식별하는 데 필수적인 정보이므로 꼭 사용하자.
    private Long id;
    private String title;
    private String description;
}
