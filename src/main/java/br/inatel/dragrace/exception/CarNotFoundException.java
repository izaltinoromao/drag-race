package br.inatel.dragrace.exception;

import br.inatel.dragrace.adapter.dto.CarRequestDto;

public class CarNotFoundException extends RuntimeException {
    public CarNotFoundException(CarRequestDto carRequestDto) {
        super("Car model: " + carRequestDto.getModel() +
                " and year: " + carRequestDto.getYear() +
                " not found at CarDataApi");
    }
}
