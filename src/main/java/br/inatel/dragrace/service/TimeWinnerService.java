package br.inatel.dragrace.service;

import br.inatel.dragrace.controller.dto.TimeWinnerDto;
import br.inatel.dragrace.mapper.DragMapper;
import br.inatel.dragrace.model.TimeWinner;
import br.inatel.dragrace.repository.TimeWinnerRepository;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service class responsible to do all the logic for the controller layer
 * @author izaltino.
 * @since 09/09/2022
 */
@Service
@NoArgsConstructor
public class TimeWinnerService {
    @Autowired
    TimeWinnerRepository timeWinnerRepository;

    /**
     * Method responsible to list all the time winners
     * @return List of timerWinnerDto
     */
    @Cacheable(value = "timeWinnersList")
    public List<TimeWinnerDto> listAllTimeWinners() {

        List<TimeWinner> timeWinners = timeWinnerRepository.findAll();
        List<TimeWinnerDto> timeWinnerDtos = DragMapper.toListTimeWinnerDto(timeWinners);
        return timeWinnerDtos;
    }

}
