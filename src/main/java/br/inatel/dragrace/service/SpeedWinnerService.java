package br.inatel.dragrace.service;

import br.inatel.dragrace.controller.dto.SpeedWinnerDto;
import br.inatel.dragrace.exception.DataBaseConnectionException;
import br.inatel.dragrace.mapper.DragMapper;
import br.inatel.dragrace.model.SpeedWinner;
import br.inatel.dragrace.repository.DragRepository;
import br.inatel.dragrace.repository.SpeedWinnerRepository;
import org.hibernate.exception.JDBCConnectionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
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

    @Cacheable(value = "speedWinnersList")
    public List<SpeedWinnerDto> listAllSpeedWinners() {
        try {
        List<SpeedWinner> speedWinners = speedWinnerRepository.findAll();
        List<SpeedWinnerDto> speedWinnerDtos = DragMapper.toListSpeedWinnerDto(speedWinners);
        return speedWinnerDtos;
        } catch (JDBCConnectionException jdbcConnectionException){
            throw new DataBaseConnectionException(jdbcConnectionException);
        }
    }
}
