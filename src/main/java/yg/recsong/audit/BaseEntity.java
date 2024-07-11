package yg.recsong.audit;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

/**
 * ■ @MappedSuperclass <p>
 *     - 엔티티 클래스가 다른 엔티티 클래스에서 공통으로 사용하는 매핑 정보를 제공할 수 있게 한다. <br>
 *     - 여러 엔티티 클래스에 공통으로 사용되는 속성(예: createdAt, updatedAt, createdBy 등)을 정의할 때 사용된다. <br>
 * </p>
 * <br>
 * ■ @EntityListeners(AuditingEntityListener.class) <p>
 *     - `@EntityListeners`는 JPA 엔티티의 생명주기 이벤트를 리스닝하는 클래스를 지정할 때 사용하는 어노테이션이다. <br>
 *     - `AuditingEntityListener`는 Spring Data JPA에서 제공하는 엔티티 리스너로, 엔티티의 생성 및 수정 시각 등의 감사를 처리한다. <br>
 * </p>
 */

@Getter
@Setter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class BaseEntity {

    @CreatedDate
    @Column(updatable = false) // 수정 불가
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime modifiedAt;

}
