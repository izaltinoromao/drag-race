package br.inatel.dragrace.service;

import br.inatel.dragrace.adapter.CarDataAdapter;
import br.inatel.dragrace.adapter.dto.CarDto;
import br.inatel.dragrace.adapter.dto.CarRequestDto;
import br.inatel.dragrace.controller.dto.DragDto;
import br.inatel.dragrace.controller.dto.SpeedWinnerDto;
import br.inatel.dragrace.controller.dto.TimeWinnerDto;
import br.inatel.dragrace.controller.form.DragForm;
import br.inatel.dragrace.model.Drag;
import br.inatel.dragrace.model.SpeedWinner;
import br.inatel.dragrace.model.TimeWinner;
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

/**
 * Unit test class for the successful test of the service layer
 * @author izaltino.
 * @since 09/09/2022
 */
@ExtendWith(MockitoExtension.class)
@SpringBootTest
@ActiveProfiles("test")
public class ServiceSuccessfulTests {

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
    @InjectMocks
    private SpeedWinnerService speedWinnerService = new SpeedWinnerService();
    @InjectMocks
    private TimeWinnerService timeWinnerService = new TimeWinnerService();

    Drag drag;
    DragForm dragForm;
    CarRequestDto carRequestDto;
    CarDto carDto;
    SpeedWinner speedWinner;
    List<SpeedWinner> speedWinners = new ArrayList<>();
    TimeWinner timeWinner;
    List<TimeWinner> timeWinners = new ArrayList<>();
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

        speedWinner = SpeedWinner.builder()
                .id(0)
                .race(0)
                .driver("Neto")
                .model("Supra")
                .speedTrap(125.25)
                .build();
        speedWinners.add(speedWinner);

        timeWinner = TimeWinner.builder()
                .id(0)
                .race(0)
                .driver("Neto")
                .model("Supra")
                .dragTime(25.25)
                .build();
        timeWinners.add(timeWinner);

        dragPage = new PageImpl<>(drags);
    }


    /**
     * This test should list successfully all the drag races
     */
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


    /**
     * This test should find a driver successfully by the driver's name
     */
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

    /**
     * This test should reset the rece successfully
     */
    @Test
    public void givenResetRace_whenResetExistentRace_shouldReturnMessage(){
        when(dragRepository.findAll()).thenReturn(drags);

        Message message = dragService.resetRace();

        assertEquals("The race was reseted successfully", message.getMessage());

    }

    /**
     * This test should register successfully a drag
     */
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

    /**
     * This test should get car information from the car data successfully
     */
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

    /**
     * This test should set the winners successfully
     */
    @Test
    public void givenSetWinners_whenSetWinnersWithDragsExisting_shouldReturnMessage(){
        when(dragRepository.raceTimeWinner()).thenReturn(drag);
        when(dragRepository.raceSpeedWinner()).thenReturn(drag);

        Message message = dragService.setWinners();

        assertEquals("The winners was set successfully", message.getMessage());
    }

    /**
     * This test should list all the speed winners successfully
     */
    @Test
    public void givenListAllSpeedWinners_whenListAllSpeedWinners_shouldReturnSpeedWinnersDtosList(){
        when(speedWinnerRepository.findAll()).thenReturn(speedWinners);

        List<SpeedWinnerDto> speedWinnerDtos = speedWinnerService.listAllSpeedWinners();

        assertEquals(speedWinners.get(0).getRace(), speedWinnerDtos.get(0).getRace());
        assertEquals(speedWinners.get(0).getDriver(), speedWinnerDtos.get(0).getDriver());
        assertEquals(speedWinners.get(0).getModel(), speedWinnerDtos.get(0).getModel());
        assertEquals(speedWinners.get(0).getSpeedTrap(), speedWinnerDtos.get(0).getSpeedTrap());
    }

    /**
     * This test should list all the time winners successfully
     */
    @Test
    public void givenListAllTimeWinners_whenListAllTimeWinners_shouldReturnTimeWinnersDtosList(){
        when(timeWinnerRepository.findAll()).thenReturn(timeWinners);

        List<TimeWinnerDto> timeWinnerDtos = timeWinnerService.listAllTimeWinners();

        assertEquals(timeWinners.get(0).getRace(), timeWinnerDtos.get(0).getRace());
        assertEquals(timeWinners.get(0).getDriver(), timeWinnerDtos.get(0).getDriver());
        assertEquals(timeWinners.get(0).getModel(), timeWinnerDtos.get(0).getModel());
        assertEquals(timeWinners.get(0).getDragTime(), timeWinnerDtos.get(0).getDragTime());
    }
}
