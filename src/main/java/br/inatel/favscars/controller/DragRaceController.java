package br.inatel.favscars.controller;

import br.inatel.favscars.adapter.dto.CarRequestDto;
import br.inatel.favscars.controller.dto.DragDto;
import br.inatel.favscars.controller.form.DragForm;
import br.inatel.favscars.model.SpeedWinner;
import br.inatel.favscars.model.TimeWinner;
import br.inatel.favscars.service.DragService;
import br.inatel.favscars.service.SpeedWinnerService;
import br.inatel.favscars.service.TimeWinnerService;
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
    public List<TimeWinner> listAllTimeWinners() {

        List<TimeWinner> timeWinners = timeWinnerService.listAllTimeWinners();

        return timeWinners;
    }

    @GetMapping("/speedwinners")
    @ResponseStatus(HttpStatus.OK)
    public List<SpeedWinner> listAllSpeedWinners() {

        List<SpeedWinner> speedWinner = speedWinnerService.listAllSpeedWinners();

        return speedWinner;
    }

    @DeleteMapping("/resetrace")
    @ResponseStatus(HttpStatus.OK)
    public void resetRace() {
        dragService.resetRace();
    }


}
