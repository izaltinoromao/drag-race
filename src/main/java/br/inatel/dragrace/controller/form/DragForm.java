package br.inatel.dragrace.controller.form;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

/**
 * @author izaltino.
 * @since 09/09/2022
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DragForm {

    @NotNull @NotEmpty
    private String driver;
    @NotNull @Positive
    private Double dragTime;
    @NotNull @Positive
    private Double speedTrap;

}
