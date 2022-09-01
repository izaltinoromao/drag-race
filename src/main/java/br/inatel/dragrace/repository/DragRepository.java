package br.inatel.dragrace.repository;

import br.inatel.dragrace.model.Drag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface DragRepository extends JpaRepository<Drag, Integer> {

    @Query(value = "SELECT * FROM drag WHERE drag_time = (SELECT MIN(drag_time) FROM drag ) limit 1 ", nativeQuery = true)
    Drag receTimeWinner();

    @Query(value = "SELECT * FROM drag WHERE speed_trap = (SELECT MAX(speed_trap) FROM drag) limit 1", nativeQuery = true)
    Drag receSpeedWinner();
}
