package br.inatel.dragrace.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DragDto {

    private String driver;
    private Double dragTime;
    private Double speedTrap;
    private int carYear;
    private String maker;
    private String model;
}
