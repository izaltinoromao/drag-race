package br.inatel.favscars.service;

import br.inatel.favscars.adapter.CarDataAdapter;
import br.inatel.favscars.adapter.dto.CarDto;
import br.inatel.favscars.adapter.dto.CarRequestDto;
import br.inatel.favscars.controller.dto.DragDto;
import br.inatel.favscars.controller.form.DragForm;
import br.inatel.favscars.mapper.DragMapper;
import br.inatel.favscars.model.Drag;
import br.inatel.favscars.model.SpeedWinner;
import br.inatel.favscars.model.TimeWinner;
import br.inatel.favscars.repository.DragRepository;
import br.inatel.favscars.repository.SpeedWinnerRepository;
import br.inatel.favscars.repository.TimeWinnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public DragDto newDrag(CarRequestDto carRequestDto, DragForm dragForm) {
        CarDto carDto = carDataAdapter.getCar(carRequestDto);
        Drag drag = new Drag(carDto, dragForm);

        if(carDto == null){
            return null;
        }else {
            dragRepository.save(drag);
        }
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
}
