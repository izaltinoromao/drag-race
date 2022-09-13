package br.inatel.dragrace.exception;

import br.inatel.dragrace.adapter.dto.CarRequestDto;

/**
 * Exception class when the car doesn't exist at CarDataAPI
 * @author izaltino.
 * @since 09/09/2022
 */
public class CarNotFoundException extends RuntimeException {
    /**
     * @param carRequestDto
     */
    public CarNotFoundException(CarRequestDto carRequestDto) {
        super("Car model: " + carRequestDto.getModel() +
                " and year: " + carRequestDto.getYear() +
                " not found at CarDataApi");
    }
}
