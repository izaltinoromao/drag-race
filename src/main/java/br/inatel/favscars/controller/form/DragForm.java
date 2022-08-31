package br.inatel.favscars.controller.form;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DragForm {

    @NotNull @NotEmpty @Length(min = 3)
    private String driver;
    @NotNull
    private Double dragTime;
    @NotNull
    private Double speedTrap;

}
