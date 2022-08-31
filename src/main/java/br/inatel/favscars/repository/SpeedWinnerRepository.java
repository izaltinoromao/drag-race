package br.inatel.favscars.repository;

import br.inatel.favscars.model.SpeedWinner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface SpeedWinnerRepository extends JpaRepository<SpeedWinner, Integer> {


}
