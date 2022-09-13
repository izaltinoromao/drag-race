package br.inatel.dragrace.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * @author izaltino.
 * @since 09/09/2022
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class SpeedWinner {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int race;
    private String driver;
    private String model;
    private Double speedTrap;

    public SpeedWinner(Drag drag) {
        this.driver = drag.getDriver();
        this.model = drag.getModel();
        this.speedTrap = drag.getSpeedTrap();
    }
}
