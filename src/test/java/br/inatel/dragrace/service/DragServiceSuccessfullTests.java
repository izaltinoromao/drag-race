package br.inatel.dragrace.service;

import br.inatel.dragrace.controller.dto.DragDto;
import br.inatel.dragrace.model.Drag;
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

import static org.mockito.Mockito.when;

import static org.junit.jupiter.api.Assertions.*;


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
    @InjectMocks
    private DragService dragService = new DragService();

    Drag drag;
    List<Drag> drags = new ArrayList<>();
    Page<Drag> dragPage;
    Pageable page = PageRequest.of(0, 10);

    @BeforeEach
    public void init(){
        drag = Drag.builder()
                .id(1)
                .driver("neto")
                .dragTime(25.25)
                .speedTrap(125.25)
                .carYear(2019)
                .make("Toyota")
                .model("Prius")
                .type("Sedan")
                .build();
        drags.add(drag);

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


}
