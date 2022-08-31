package br.inatel.favscars.service;

import br.inatel.favscars.model.Drag;
import br.inatel.favscars.model.TimeWinner;
import br.inatel.favscars.repository.DragRepository;
import br.inatel.favscars.repository.TimeWinnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TimeWinnerService {

    TimeWinnerRepository timeWinnerRepository;
    DragRepository dragRepository;

    @Autowired
    public TimeWinnerService(TimeWinnerRepository timeWinnerRepository, DragRepository dragRepository){
        this.timeWinnerRepository = timeWinnerRepository;
        this.dragRepository = dragRepository;
    }


    public List<TimeWinner> listAllTimeWinners() {
        List<TimeWinner> timeWinners = timeWinnerRepository.findAll();
        return timeWinners;
    }

    public TimeWinner setTimeWinner() {
        Drag drag = dragRepository.receTimeWinner();
        TimeWinner timeWinner = new TimeWinner(drag);
        timeWinnerRepository.save(timeWinner);
        return timeWinner;
    }
}
