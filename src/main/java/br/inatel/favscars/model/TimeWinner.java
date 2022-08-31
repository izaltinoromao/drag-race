package br.inatel.favscars.model;

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
public class TimeWinner {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int race;
    private String driver;
    private String model;
    private Double dragTime;

}
