package yg.recsong.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import yg.recsong.audit.BaseEntity;

@Entity
@Table(name = "post")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Post extends BaseEntity {
    /**
     * 게시글 (Post) <p>
     *
     * - id : 게시글 아이디 <br>
     *
     * - title : 게시글 제목 <br>
     *
     * - username : 게시글 작성자 <br>
     *
     * - content : 게시글 내용 <br>
     *
     * - createdDate : 게시글 생성일 (BaseEntity로 상속)<br>
     *
     * - modifyDate : 게시글 수정일 (BaseEntity로 상속) <br>
     *
     * - comments : 게시글에 들어있는 댓글 <br>
     */

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String author;

    @Column(name = "post_content", length = 1024)
    private String content;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> comments = new ArrayList<>();

    // Post와 Board 관계 설정
    @ManyToOne
    @JoinColumn(name = "board_id")
    private Board board;

}
