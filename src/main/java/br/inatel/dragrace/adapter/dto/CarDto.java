package br.inatel.dragrace.adapter.dto;

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
public class CarDto {

    private int id;
    private int year;
    private String make;
    private String model;
    private String type;

}
