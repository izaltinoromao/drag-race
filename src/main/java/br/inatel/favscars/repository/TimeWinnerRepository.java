package br.inatel.favscars.repository;

import br.inatel.favscars.model.TimeWinner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface TimeWinnerRepository extends JpaRepository<TimeWinner, Integer> {


}
