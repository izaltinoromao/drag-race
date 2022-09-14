package br.inatel.dragrace.service;

import br.inatel.dragrace.adapter.CarDataAdapter;
import br.inatel.dragrace.adapter.dto.CarDto;
import br.inatel.dragrace.adapter.dto.CarRequestDto;
import br.inatel.dragrace.controller.dto.DragDto;
import br.inatel.dragrace.controller.form.DragForm;
import br.inatel.dragrace.exception.*;
import br.inatel.dragrace.model.Drag;
import br.inatel.dragrace.model.SpeedWinner;
import br.inatel.dragrace.model.TimeWinner;
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

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.catchThrowable;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

/**
 * Unit test class for the failures test of the service layer
 * @author izaltino.
 * @since 09/09/2022
 */
@ExtendWith(MockitoExtension.class)
@SpringBootTest
@ActiveProfiles("test")
public class ServiceFailuresTests {

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
    List<Drag> dragsEmpty = new ArrayList<>();
    Page<Drag> dragPage;
    Page<Drag> dragPageEmpty;
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
                .race(0)
                .driver("Neto")
                .model("Supra")
                .speedTrap(125.25)
                .build();
        speedWinners.add(speedWinner);

        timeWinner = TimeWinner.builder()
                .race(0)
                .driver("Neto")
                .model("Supra")
                .dragTime(25.25)
                .build();
        timeWinners.add(timeWinner);


        dragPage = new PageImpl<>(drags);
        dragPageEmpty = new PageImpl<>(dragsEmpty);
    }

    /**
     * This test should return an empty list since there's no drags
     */
    @Test
    public void givenListAllDrags_whenListAlDragsIsEmpty_shouldReturnDragDtoListEmpty(){
        when(dragRepository.findAll(page)).thenReturn(dragPageEmpty);

        Page<DragDto> dragDtos = dragService.listALlDrags(page);
        List<DragDto> dragDtosList = dragDtos.stream().toList();

        assertEquals(0, dragDtosList.size());
        assertTrue(dragDtosList.isEmpty());

    }

    /**
     * This test should throw an CarNotFoundException since no found the car at the CarDataAPI
     */
    @Test
    public void givenGetFromCarData_whenGetAInvalidCarFromCarData_shouldCarNotFoundException(){
        when(carDataAdapter.getCar(carRequestDto)).thenReturn(null);

        Throwable throwable = catchThrowable(() -> dragService.getCarFromCarData(carRequestDto));

        assertThat(throwable).isInstanceOf(CarNotFoundException.class)
                .hasMessage("Car model: " + carRequestDto.getModel() +
                        " and year: " + carRequestDto.getYear() +
                        " not found at CarDataApi");
    }

    /**
     * This test should throw an DragAlreadyExistsException since the driver already have one drag
     */
    @Test
    public void givenNewDrag_whenNewAlreadyExitentRace_shouldReturnDragAlreadyExistsException(){
        when(dragRepository.findByDriver(dragForm.getDriver())).thenReturn(drag);

        Throwable throwable = catchThrowable(() -> dragService.newDrag(carRequestDto, dragForm));

        assertThat(throwable).isInstanceOf(DragAlreadyExistsException.class)
                .hasMessage("The driver " + drag.getDriver() + " already have one drag");
    }

    /**
     * This test should throw an NoDriverFoundException since try to find a drag with a non-existent driver
     */
    @Test
    public void givenFindDragByDriver_whenFindDragByNonExistentDriver_shouldReturnNoDriverFoundException(){
        when(dragRepository.findByDriver(any(String.class))).thenReturn(null);

        Throwable throwable = catchThrowable(() -> dragService.findDragByDriver(drag.getDriver()));

        assertThat(throwable).isInstanceOf(NoDriverFoundException.class)
                .hasMessage("No driver " + drag.getDriver() + " was found at Data Base");
    }

    /**
     * This test should throw an NoDragsAtRaceYetException when set winners with no drags registered yet
     */
    @Test
    public void givenSetWinners_whenSetWinnersWithNoDrags_shouldReturnNoDragsAtRaceYetException(){
        when(dragRepository.raceTimeWinner()).thenReturn(null);
        when(dragRepository.raceSpeedWinner()).thenReturn(null);

        Throwable throwable = catchThrowable(() -> dragService.setWinners());

        assertThat(throwable).isInstanceOf(NoDragsAtRaceYetException.class)
                .hasMessage("There's no drags at the race yet");
    }

    /**
     * This test should throw an NothingToDeleteException since there's no drags at a rece to delete
     */
    @Test
    public void givenResetRace_whenResetNonExistentRace_shouldReturnNothingToDeleteException(){
        when(dragRepository.findAll()).thenReturn(dragsEmpty);

        Throwable throwable = catchThrowable(() -> dragService.resetRace());

        assertThat(throwable).isInstanceOf(NothingToDeleteException.class)
                .hasMessage("The race is already reseted");
    }
}
