package br.inatel.dragrace.repository;

import br.inatel.dragrace.model.SpeedWinner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface SpeedWinnerRepository extends JpaRepository<SpeedWinner, Integer> {


}
