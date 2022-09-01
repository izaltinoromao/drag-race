package br.inatel.favscars.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SpeedWinnerDto {

    private int race;
    private String driver;
    private String model;
    private Double speedTrap;
}