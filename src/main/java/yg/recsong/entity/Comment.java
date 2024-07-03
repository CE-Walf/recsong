package yg.recsong.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "comment")
@Getter
@Setter
@NoArgsConstructor
public class Comment {

    /**
     * 댓글 (Comment) <p>
     *
     * - id : 댓글 아이디 <br>
     *
     * - username : 댓글을 단 사람 <br>
     *
     * - content : 댓글 내용 <br>
     */

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    private String content;

    @ManyToOne
    @JoinColumn(name="post_id")
    private Post post;
}
