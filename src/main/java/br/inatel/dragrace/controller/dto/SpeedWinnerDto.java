package br.inatel.dragrace.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author izaltino.
 * @since 09/09/2022
 */
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
