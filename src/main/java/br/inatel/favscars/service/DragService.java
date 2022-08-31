package br.inatel.favscars.service;

import br.inatel.favscars.adapter.CarDataAdapter;
import br.inatel.favscars.adapter.dto.CarDto;
import br.inatel.favscars.adapter.dto.CarRequestDto;
import br.inatel.favscars.controller.dto.DragDto;
import br.inatel.favscars.controller.form.DragForm;
import br.inatel.favscars.mapper.DragMapper;
import br.inatel.favscars.model.Drag;
import br.inatel.favscars.repository.DragRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DragService {

    private DragRepository dragRepository;
    private CarDataAdapter carDataAdapter;

    @Autowired
    public DragService(CarDataAdapter carDataAdapter, DragRepository dragRepository){
        this.carDataAdapter = carDataAdapter;
        this.dragRepository = dragRepository;
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
}
