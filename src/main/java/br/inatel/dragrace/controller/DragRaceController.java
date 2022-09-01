package br.inatel.dragrace.controller;

import br.inatel.dragrace.adapter.dto.CarRequestDto;
import br.inatel.dragrace.controller.dto.DragDto;
import br.inatel.dragrace.controller.dto.SpeedWinnerDto;
import br.inatel.dragrace.controller.dto.TimeWinnerDto;
import br.inatel.dragrace.controller.form.DragForm;
import br.inatel.dragrace.model.rest.Message;
import br.inatel.dragrace.service.DragService;
import br.inatel.dragrace.service.SpeedWinnerService;
import br.inatel.dragrace.service.TimeWinnerService;
import com.sun.istack.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/favscars")
public class DragRaceController {

    @Autowired
    private DragService dragService;
    @Autowired
    private TimeWinnerService timeWinnerService;

    @Autowired
    private SpeedWinnerService speedWinnerService;

    @GetMapping("/makers")
    @ResponseStatus(HttpStatus.OK)
    public List<String> listALlMakers() {

        List<String> makers = dragService.listAllMakers();

        return makers;
    }

    @PostMapping ("/newdrag")
    @ResponseStatus(HttpStatus.CREATED)
    public DragDto addDrag(@RequestParam @NotNull Integer year, @RequestParam @NotNull String model, @RequestBody @Valid DragForm dragForm){

        CarRequestDto carRequestDto = new CarRequestDto(year, model);

        DragDto dragDto = dragService.newDrag(carRequestDto, dragForm);

        return dragDto;
    }

    @GetMapping("/listdrags")
    @ResponseStatus(HttpStatus.OK)
    public List<DragDto> listAllDrags() {

        List<DragDto> dragDtos = dragService.listALlDrags();

        return dragDtos;
    }

    @GetMapping("/setwinners")
    @ResponseStatus(HttpStatus.OK)
    public void setTimeWinner() {
        dragService.setWinners();
    }

    @GetMapping("/timewinners")
    @ResponseStatus(HttpStatus.OK)
    public List<TimeWinnerDto> listAllTimeWinners() {

        List<TimeWinnerDto> timeWinnerDtos = timeWinnerService.listAllTimeWinners();

        return timeWinnerDtos;
    }

    @GetMapping("/speedwinners")
    @ResponseStatus(HttpStatus.OK)
    public List<SpeedWinnerDto> listAllSpeedWinners() {

        List<SpeedWinnerDto> speedWinnerDtos = speedWinnerService.listAllSpeedWinners();

        return speedWinnerDtos;
    }

    @DeleteMapping("/resetrace")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Message resetRace() {
        return dragService.resetRace();
    }


}