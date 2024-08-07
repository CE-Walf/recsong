package yg.recsong.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.GenerationType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "board")
public class Board {

    /**
     * 게시판 (Board) <p>
     *
     * - id : 게시판 아이디 <br>
     *
     * - title : 게시판의 이름 <br>
     *
     * - description : 게시판 설명 <br>
     *
     * - posts : 게시판에 들어있는 게시글 <br>
     */

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String description;

    @OneToMany(mappedBy = "board", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default // 빌더를 통해 객체를 생성할 때 comments 필드의 초기값으로 새로운 ArrayList가 사용
    private List<Post> posts = new ArrayList<>();
}
