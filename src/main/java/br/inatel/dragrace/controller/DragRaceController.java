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
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Controller class is responsible to deal with the REST requests
 * @author izaltino.
 * @since 09/09/2022
 */
@RestController
@RequestMapping("/drag-race")
public class DragRaceController {

    @Autowired
    private DragService dragService;
    @Autowired
    private TimeWinnerService timeWinnerService;

    @Autowired
    private SpeedWinnerService speedWinnerService;

    /**
     * Method responsible to resgister a new drag at the race from a POST request
     * @param year
     * @param model
     * @param dragForm
     * @return dragDto with the information that were registered
     */
    @ApiResponses(value = { @ApiResponse(code = 201, message = "", response = DragDto.class),
            @ApiResponse(code = 400, message = "<field> must not be empty"),
            @ApiResponse(code = 400, message = "<parameter> parameter is required"),
            @ApiResponse(code = 404, message = "Car model <model> and year <year> not found at CarDataApi"),
            @ApiResponse(code = 409, message = "The driver <diver> already have one drag"),
            @ApiResponse(code = 500, message = "Exception Specialized Message."),
            @ApiResponse(code = 503, message = "External Api Connection fail."),
            @ApiResponse(code = 503, message = "JDBC Connection fail.")})
    @PostMapping ("/newdrag")
    @ResponseStatus(HttpStatus.CREATED)
    public DragDto addDrag(@RequestParam @NotNull Integer year, @RequestParam @NotNull String model, @RequestBody @Valid DragForm dragForm){

        CarRequestDto carRequestDto = new CarRequestDto(year, model);

        return dragService.newDrag(carRequestDto, dragForm);
    }

    /**
     * Method responsible to list all drags already registered at the database from a GET request
     * @param page
     * @return Page of DragDto
     */
    @ApiResponses(value = { @ApiResponse(code = 200, message = "", response = DragDto.class),
            @ApiResponse(code = 500, message = "Exception Specialized Message."),
            @ApiResponse(code = 503, message = "External Api Connection fail."),
            @ApiResponse(code = 503, message = "JDBC Connection fail.")})
    @GetMapping(value = "/listdrags")
    @ResponseStatus(HttpStatus.OK)
    public Page<DragDto> listAllDrags(@PageableDefault(page = 0, size = 10) Pageable page) {

        return dragService.listALlDrags(page);
    }

    /**
     * Method responsible to get only a drag by driver's name from a GET request
     * @param driver
     * @return dragDto
     */
    @ApiResponses(value = { @ApiResponse(code = 200, message = "", response = DragDto.class),
            @ApiResponse(code = 404, message = "No driver <driver> was found at Data Base"),
            @ApiResponse(code = 500, message = "Exception Specialized Message."),
            @ApiResponse(code = 503, message = "External Api Connection fail."),
            @ApiResponse(code = 503, message = "JDBC Connection fail.")})
    @GetMapping(value = "/drag")
    @ResponseStatus(HttpStatus.OK)
    public DragDto getDragByDriver(@RequestParam @NotNull String driver){

        DragDto dragDto = dragService.findDragByDriver(driver);

        return dragDto;
    }

    /**
     * Method responsible only to set the winners from a race from a POST request
     * @return Message
     */
    @ApiResponses(value = { @ApiResponse(code = 200, message = "", response = Message.class),
            @ApiResponse(code = 404, message = "There's no drags at the race yet"),
            @ApiResponse(code = 500, message = "Exception Specialized Message."),
            @ApiResponse(code = 503, message = "External Api Connection fail."),
            @ApiResponse(code = 503, message = "JDBC Connection fail.")})
    @PostMapping("/setwinners")
    @ResponseStatus(HttpStatus.OK)
    public Message setWinners() {
        return dragService.setWinners();
    }

    /**
     * Method responsible to list all the Time Winners from a GET request
     * @return List of TimeWinnerDto
     */
    @ApiResponses(value = { @ApiResponse(code = 200, message = "", response = TimeWinnerDto.class),
            @ApiResponse(code = 500, message = "Exception Specialized Message."),
            @ApiResponse(code = 503, message = "External Api Connection fail."),
            @ApiResponse(code = 503, message = "JDBC Connection fail.")})
    @GetMapping("/timewinners")
    @ResponseStatus(HttpStatus.OK)
    public List<TimeWinnerDto> listAllTimeWinners() {

        return timeWinnerService.listAllTimeWinners();
    }

    /**
     * Method responsible to list all the Speed Winners from a GET request
     * @return List of SpeedWinnerDto
     */
    @ApiResponses(value = { @ApiResponse(code = 200, message = "", response = SpeedWinnerDto.class),
            @ApiResponse(code = 500, message = "Exception Specialized Message."),
            @ApiResponse(code = 503, message = "External Api Connection fail."),
            @ApiResponse(code = 503, message = "JDBC Connection fail.")})
    @GetMapping("/speedwinners")
    @ResponseStatus(HttpStatus.OK)
    public List<SpeedWinnerDto> listAllSpeedWinners() {

        return speedWinnerService.listAllSpeedWinners();
    }

    /**
     * Method responsible only to reset the race, all the drags at the current race will be wiped from a DELETE request
     * @return Message
     */
    @ApiResponses(value = { @ApiResponse(code = 204, message = "", response = Message.class),
            @ApiResponse(code = 404, message = "There's no drags at the race yet"),
            @ApiResponse(code = 500, message = "Exception Specialized Message."),
            @ApiResponse(code = 503, message = "External Api Connection fail."),
            @ApiResponse(code = 503, message = "JDBC Connection fail.")})
    @DeleteMapping("/resetrace")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Message resetRace() {
        return dragService.resetRace();
    }


}
