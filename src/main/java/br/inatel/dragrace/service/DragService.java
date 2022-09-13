package br.inatel.dragrace.service;

import br.inatel.dragrace.adapter.CarDataAdapter;
import br.inatel.dragrace.adapter.dto.CarDto;
import br.inatel.dragrace.adapter.dto.CarRequestDto;
import br.inatel.dragrace.controller.dto.DragDto;
import br.inatel.dragrace.controller.form.DragForm;
import br.inatel.dragrace.exception.*;
import br.inatel.dragrace.mapper.DragMapper;
import br.inatel.dragrace.model.Drag;
import br.inatel.dragrace.model.SpeedWinner;
import br.inatel.dragrace.model.TimeWinner;
import br.inatel.dragrace.model.rest.Message;
import br.inatel.dragrace.repository.DragRepository;
import br.inatel.dragrace.repository.SpeedWinnerRepository;
import br.inatel.dragrace.repository.TimeWinnerRepository;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service class responsible to do all the logic for the controller layer
 * @author izaltino.
 * @since 09/09/2022
 */
@Service
@NoArgsConstructor
public class DragService {

    @Autowired
    private DragRepository dragRepository;
    @Autowired
    private TimeWinnerRepository timeWinnerRepository;
    @Autowired
    private SpeedWinnerRepository speedWinnerRepository;
    @Autowired
    private CarDataAdapter carDataAdapter;

    /**
     * Method responsible to get a car from the CarDataAPI
     * @param carRequestDto
     * @return carDto
     */
    public CarDto getCarFromCarData(CarRequestDto carRequestDto) {

            CarDto carDto = carDataAdapter.getCar(carRequestDto);
            if(carDto != null){
                return carDto;
            }else {
                throw new CarNotFoundException(carRequestDto);
            }


    }

    /**
     * Method responsible to register a new drag at the database
     * @param carRequestDto
     * @param dragForm
     * @return DragDto
     */
    @CacheEvict(value = "dragList", allEntries = true)
    public DragDto newDrag(CarRequestDto carRequestDto, DragForm dragForm) {

            Drag verifierDrag = dragRepository.findByDriver(dragForm.getDriver());
            if (verifierDrag != null) {
                throw new DragAlreadyExistsException(dragForm.getDriver());
            } else {
                CarDto carDto = getCarFromCarData(carRequestDto);
                Drag drag = new Drag(carDto, dragForm);
                dragRepository.save(drag);
                return DragMapper.toDragDto(drag);
            }
    }

    /**
     * Method responsible to list all the drags registered for a race
     * @param page
     * @return Page of dragDto
     */
    @Cacheable(value = "dragList")
    public Page<DragDto> listALlDrags(Pageable page) {

            Page<Drag> drags = dragRepository.findAll(page);
            Page<DragDto> dragDtos = DragMapper.toListDragDto(drags);
            return dragDtos;
    }

    /**
     * Method responsible to get a drag by a driver
     * @param driver
     * @return dragDto
     */
    public DragDto findDragByDriver(String driver) {

        Drag drag = dragRepository.findByDriver(driver);
        if(drag == null)
            throw new NoDriverFoundException(driver);
        else {
            DragDto dragDto = DragMapper.toDragDto(drag);
            return dragDto;
        }

    }

    /**
     * Method responsible to set the winners of a race
     * @return Message
     */
    @CacheEvict(value = {"speedWinnersList", "timeWinnersList"}, allEntries = true)
    public Message setWinners() {

            Drag raceTimeWinner = dragRepository.raceTimeWinner();
            Drag raceSpeedWinner = dragRepository.raceSpeedWinner();
            if(raceSpeedWinner == null || raceTimeWinner == null){
                throw new NoDragsAtRaceYetException();
            } else {
                TimeWinner timeWinner = new TimeWinner(raceTimeWinner);
                SpeedWinner speedWinner = new SpeedWinner(raceSpeedWinner);
                timeWinnerRepository.save(timeWinner);
                speedWinnerRepository.save(speedWinner);
                return new Message("The winners was set successfully");
            }
    }

    /**
     * Method responsible to resert the race wiping all the drags
     * @return Message
     */
    public Message resetRace() {

            List<Drag> drags = dragRepository.findAll();
            if(drags.isEmpty()){
                throw new NothingToDeleteException();
            }else {
                dragRepository.deleteAll();
                return new Message("The race was reseted successfully");
            }
    }


}
