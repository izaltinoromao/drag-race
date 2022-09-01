package br.inatel.dragrace.service;

import br.inatel.dragrace.controller.dto.TimeWinnerDto;
import br.inatel.dragrace.exception.DataBaseConnectionException;
import br.inatel.dragrace.mapper.DragMapper;
import br.inatel.dragrace.model.TimeWinner;
import br.inatel.dragrace.repository.TimeWinnerRepository;
import org.hibernate.exception.JDBCConnectionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TimeWinnerService {

    TimeWinnerRepository timeWinnerRepository;


    @Autowired
    public TimeWinnerService(TimeWinnerRepository timeWinnerRepository){
        this.timeWinnerRepository = timeWinnerRepository;
    }

    @Cacheable(value = "timeWinnersList")
    public List<TimeWinnerDto> listAllTimeWinners() {
        try {
        List<TimeWinner> timeWinners = timeWinnerRepository.findAll();
        List<TimeWinnerDto> timeWinnerDtos = DragMapper.toListTimeWinnerDto(timeWinners);
        return timeWinnerDtos;
        } catch (JDBCConnectionException jdbcConnectionException){
            throw new DataBaseConnectionException(jdbcConnectionException);
        }
    }

}
