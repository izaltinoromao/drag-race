package br.inatel.dragrace.repository;

import br.inatel.dragrace.model.TimeWinner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface TimeWinnerRepository extends JpaRepository<TimeWinner, Integer> {


}
