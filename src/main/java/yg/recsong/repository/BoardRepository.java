package yg.recsong.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import yg.recsong.entity.Board;

@Repository
public interface BoardRepository extends JpaRepository<Board, Long> {

}
