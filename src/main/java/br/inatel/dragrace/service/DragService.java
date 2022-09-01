package br.inatel.dragrace.service;

import br.inatel.dragrace.adapter.CarDataAdapter;
import br.inatel.dragrace.adapter.dto.CarDto;
import br.inatel.dragrace.adapter.dto.CarRequestDto;
import br.inatel.dragrace.controller.dto.DragDto;
import br.inatel.dragrace.controller.form.DragForm;
import br.inatel.dragrace.exception.CarDataApiConnectionException;
import br.inatel.dragrace.exception.CarNotFoundException;
import br.inatel.dragrace.mapper.DragMapper;
import br.inatel.dragrace.model.Drag;
import br.inatel.dragrace.model.SpeedWinner;
import br.inatel.dragrace.model.TimeWinner;
import br.inatel.dragrace.repository.DragRepository;
import br.inatel.dragrace.repository.SpeedWinnerRepository;
import br.inatel.dragrace.repository.TimeWinnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClientException;

import java.util.List;

@Service
public class DragService {

    private DragRepository dragRepository;
    private TimeWinnerRepository timeWinnerRepository;
    private SpeedWinnerRepository speedWinnerRepository;
    private CarDataAdapter carDataAdapter;

    @Autowired
    public DragService(CarDataAdapter carDataAdapter, DragRepository dragRepository, TimeWinnerRepository timeWinnerRepository, SpeedWinnerRepository speedWinnerRepository){
        this.carDataAdapter = carDataAdapter;
        this.dragRepository = dragRepository;
        this.speedWinnerRepository = speedWinnerRepository;
        this.timeWinnerRepository = timeWinnerRepository;
    }
    public List<String> listAllMakers() {

        List<String> makers;

        makers = carDataAdapter.getAllMakers();

        return makers;
    }

    public CarDto getCarFromCarData(CarRequestDto carRequestDto) {
        try {
            CarDto carDto = carDataAdapter.getCar(carRequestDto);
            if(carDto != null){
              return carDto;
            }else {
                throw new CarNotFoundException(carRequestDto);
            }

        }catch (WebClientException webClientException){
            throw new CarDataApiConnectionException(webClientException);
        }

    }
    public DragDto newDrag(CarRequestDto carRequestDto, DragForm dragForm) {
        CarDto carDto = getCarFromCarData(carRequestDto);
        Drag drag = new Drag(carDto, dragForm);
        dragRepository.save(drag);
        return DragMapper.toDragDto(drag);
    }
    public List<DragDto> listALlDrags() {
        List<Drag> drags = dragRepository.findAll();
        List<DragDto> dragDtos = DragMapper.toListDragDto(drags);
        return dragDtos;
    }

    public void setWinners() {
        Drag raceTimeWinner = dragRepository.receTimeWinner();
        Drag raceSpeedWinner = dragRepository.receSpeedWinner();
        TimeWinner timeWinner = new TimeWinner(raceTimeWinner);
        SpeedWinner speedWinner = new SpeedWinner(raceSpeedWinner);
        timeWinnerRepository.save(timeWinner);
        speedWinnerRepository.save(speedWinner);
    }

    public void resetRace() {
        dragRepository.deleteAll();
    }
}
