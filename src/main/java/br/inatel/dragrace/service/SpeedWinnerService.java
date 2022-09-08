package br.inatel.dragrace.service;

import br.inatel.dragrace.controller.dto.SpeedWinnerDto;
import br.inatel.dragrace.mapper.DragMapper;
import br.inatel.dragrace.model.SpeedWinner;
import br.inatel.dragrace.repository.SpeedWinnerRepository;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@NoArgsConstructor
public class SpeedWinnerService {
    @Autowired
    SpeedWinnerRepository speedWinnerRepository;

    @Cacheable(value = "speedWinnersList")
    public List<SpeedWinnerDto> listAllSpeedWinners() {

        List<SpeedWinner> speedWinners = speedWinnerRepository.findAll();
        List<SpeedWinnerDto> speedWinnerDtos = DragMapper.toListSpeedWinnerDto(speedWinners);
        return speedWinnerDtos;
    }
}
