package br.inatel.favscars.model;

import br.inatel.favscars.adapter.dto.CarDto;
import br.inatel.favscars.controller.form.DragForm;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Drag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String driver;
    private Double dragTime;
    private Double speedTrap;
    private int carYear;
    private String make;
    private String model;
    private String type;

    public Drag(CarDto carDto, DragForm dragForm) {

        this.driver = dragForm.getDriver();
        this.dragTime = dragForm.getDragTime();
        this.speedTrap = dragForm.getSpeedTrap();
        this.carYear = carDto.getYear();
        this.make = carDto.getMake();
        this.model = carDto.getModel();
        this.type =carDto.getType();
    }

}
