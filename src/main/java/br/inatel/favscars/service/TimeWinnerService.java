package br.inatel.favscars.service;

import br.inatel.favscars.model.TimeWinner;
import br.inatel.favscars.repository.TimeWinnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TimeWinnerService {

    TimeWinnerRepository timeWinnerRepository;


    @Autowired
    public TimeWinnerService(TimeWinnerRepository timeWinnerRepository){
        this.timeWinnerRepository = timeWinnerRepository;
    }


    public List<TimeWinner> listAllTimeWinners() {
        List<TimeWinner> timeWinners = timeWinnerRepository.findAll();
        return timeWinners;
    }

}
