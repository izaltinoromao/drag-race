package br.inatel.favscars.service;

import br.inatel.favscars.model.SpeedWinner;
import br.inatel.favscars.repository.DragRepository;
import br.inatel.favscars.repository.SpeedWinnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SpeedWinnerService {

    SpeedWinnerRepository speedWinnerRepository;
    DragRepository dragRepository;

    @Autowired
    public SpeedWinnerService(SpeedWinnerRepository speedWinnerRepository, DragRepository dragRepository){
        this.speedWinnerRepository = speedWinnerRepository;
        this.dragRepository = dragRepository;
    }

    public List<SpeedWinner> listAllSpeedWinners() {
        List<SpeedWinner> speedWinner = speedWinnerRepository.findAll();
        return speedWinner;
    }
}
