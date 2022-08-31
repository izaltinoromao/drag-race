package br.inatel.favscars.repository;

import br.inatel.favscars.model.Drag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface DragRepository extends JpaRepository<Drag, Integer> {

    @Query(value = "SELECT * FROM drag WHERE drag_time = (SELECT MIN(drag_time) FROM drag) ", nativeQuery = true)
    Drag receTimeWinner();
}
