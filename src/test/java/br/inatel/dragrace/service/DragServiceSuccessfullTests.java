package br.inatel.dragrace.service;

import br.inatel.dragrace.adapter.CarDataAdapter;
import br.inatel.dragrace.adapter.dto.CarDto;
import br.inatel.dragrace.adapter.dto.CarRequestDto;
import br.inatel.dragrace.controller.dto.DragDto;
import br.inatel.dragrace.controller.form.DragForm;
import br.inatel.dragrace.model.Drag;
import br.inatel.dragrace.model.rest.Message;
import br.inatel.dragrace.repository.DragRepository;
import br.inatel.dragrace.repository.SpeedWinnerRepository;
import br.inatel.dragrace.repository.TimeWinnerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ActiveProfiles;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
@SpringBootTest
@ActiveProfiles("test")
public class DragServiceSuccessfullTests {

    @Mock
    private DragRepository dragRepository;
    @Mock
    private TimeWinnerRepository timeWinnerRepository;
    @Mock
    private SpeedWinnerRepository speedWinnerRepository;
    @Mock
    private CarDataAdapter carDataAdapter;
    @InjectMocks
    private DragService dragService = new DragService();

    Drag drag;
    DragForm dragForm;
    CarRequestDto carRequestDto;
    CarDto carDto;
    List<Drag> drags = new ArrayList<>();
    Page<Drag> dragPage;
    Pageable page = PageRequest.of(0, 10);

    @BeforeEach
    public void init(){
        drag = Drag.builder()
                .id(0)
                .driver("Neto")
                .dragTime(25.25)
                .speedTrap(125.25)
                .carYear(2020)
                .make("Toyota")
                .model("Supra")
                .type("Coupe")
                .build();
        drags.add(drag);

        dragForm = DragForm.builder()
                .driver("Neto")
                .dragTime(25.25)
                .speedTrap(125.25)
                .build();

        carRequestDto = CarRequestDto.builder()
                .year(2020)
                .model("Supra")
                .build();

        carDto = CarDto.builder()
                .id(1234)
                .year(2020)
                .make("Toyota")
                .model("Supra")
                .type("Coupe")
                .build();



        dragPage = new PageImpl<>(drags);
    }

    @Test
    public void givenListAllDrags_whenListALlDrags_shouldReturnDragDtoList(){
        when(dragRepository.findAll(page)).thenReturn(dragPage);

        Page<DragDto> dragDtos = dragService.listALlDrags(page);
        List<DragDto> dragDtosList = dragDtos.stream().toList();

        assertEquals(dragDtosList.size(),1);
        assertEquals(dragDtosList.get(0).getDriver(), drag.getDriver());
        assertEquals(dragDtosList.get(0).getDragTime(), drag.getDragTime());
        assertEquals(dragDtosList.get(0).getSpeedTrap(), drag.getSpeedTrap());
        assertEquals(dragDtosList.get(0).getCarYear(), drag.getCarYear());
        assertEquals(dragDtosList.get(0).getMaker(), drag.getMake());
        assertEquals(dragDtosList.get(0).getModel(), drag.getModel());
    }

    @Test
    public void givenFindDragByDriver_whenFindDragByValidDriver_shouldReturnDragDto(){
        when(dragRepository.findByDriver(any(String.class))).thenReturn(drag);

        DragDto dragDto = dragService.findDragByDriver(drag.getDriver());

        assertEquals(drag.getDriver(), dragDto.getDriver());
        assertEquals(drag.getDragTime(), dragDto.getDragTime());
        assertEquals(drag.getSpeedTrap(), dragDto.getSpeedTrap());
        assertEquals(drag.getCarYear(), dragDto.getCarYear());
        assertEquals(drag.getMake(), dragDto.getMaker());
        assertEquals(drag.getModel(), dragDto.getModel());

    }

    @Test
    public void givenResetRace_whenResetExistentRace_shouldReturnMessage(){
        when(dragRepository.findAll()).thenReturn(drags);

        Message message = dragService.resetRace();

        assertEquals("The race was reseted successfully", message.getMessage());

    }

    @Test
    public void givenNewDrag_whenNewValidRace_shouldReturnDragDto(){
        when(dragRepository.findByDriver(dragForm.getDriver())).thenReturn(null);
        when(dragRepository.save(drag)).thenReturn(drag);
        when(carDataAdapter.getCar(carRequestDto)).thenReturn(carDto);

        DragDto dragDto = dragService.newDrag(carRequestDto, dragForm);

        assertEquals(dragForm.getDriver(), dragDto.getDriver());
        assertEquals(dragForm.getDragTime(), dragDto.getDragTime());
        assertEquals(dragForm.getSpeedTrap(), dragDto.getSpeedTrap());
        assertEquals(carDto.getYear(), dragDto.getCarYear());
        assertEquals(carDto.getMake(), dragDto.getMaker());
        assertEquals(carDto.getModel(), dragDto.getModel());
    }

    @Test
    public void givenGetCarFromCarData_whenGetValidCarFromCarData_shouldReturnCarDto(){
        when(carDataAdapter.getCar(carRequestDto)).thenReturn(carDto);

        CarDto carDto1 = dragService.getCarFromCarData(carRequestDto);

        assertEquals(carDto.getId(), carDto1.getId());
        assertEquals(carDto.getYear(), carDto1.getYear());
        assertEquals(carDto.getMake(), carDto1.getMake());
        assertEquals(carDto.getModel(), carDto1.getModel());
        assertEquals(carDto.getType(), carDto1.getType());
    }

}