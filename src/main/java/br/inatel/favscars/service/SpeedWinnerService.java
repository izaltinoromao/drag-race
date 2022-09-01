package br.inatel.favscars.service;

import br.inatel.favscars.controller.dto.SpeedWinnerDto;
import br.inatel.favscars.mapper.DragMapper;
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

    public List<SpeedWinnerDto> listAllSpeedWinners() {
        List<SpeedWinner> speedWinners = speedWinnerRepository.findAll();
        List<SpeedWinnerDto> speedWinnerDtos = DragMapper.toListSpeedWinnerDto(speedWinners);
        return speedWinnerDtos;
    }
}
