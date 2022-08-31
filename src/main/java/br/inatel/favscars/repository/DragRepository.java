package br.inatel.favscars.repository;

import br.inatel.favscars.model.Drag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface DragRepository extends JpaRepository<Drag, Integer> {


}
