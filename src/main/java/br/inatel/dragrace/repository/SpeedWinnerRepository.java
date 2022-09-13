package br.inatel.dragrace.repository;

import br.inatel.dragrace.model.SpeedWinner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Class responsible to connect with the data base
 * @author izaltino.
 * @since 09/09/2022
 */
@Repository
public interface SpeedWinnerRepository extends JpaRepository<SpeedWinner, Integer> {


}
