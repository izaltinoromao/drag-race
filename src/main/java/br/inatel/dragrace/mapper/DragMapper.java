package br.inatel.dragrace.mapper;

import br.inatel.dragrace.controller.dto.DragDto;
import br.inatel.dragrace.controller.dto.SpeedWinnerDto;
import br.inatel.dragrace.controller.dto.TimeWinnerDto;
import br.inatel.dragrace.model.Drag;
import br.inatel.dragrace.model.SpeedWinner;
import br.inatel.dragrace.model.TimeWinner;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.stream.Collectors;

public class DragMapper {

    public static DragDto toDragDto(Drag drag) {

        DragDto dragDto = DragDto.builder()
                .driver(drag.getDriver())
                .dragTime(drag.getDragTime())
                .speedTrap(drag.getSpeedTrap())
                .carYear(drag.getCarYear())
                .maker(drag.getMake())
                .model(drag.getModel())
                .build();

        return dragDto;
    }

    public static TimeWinnerDto toTimeWinnerDto(TimeWinner timeWinner) {

        TimeWinnerDto timeWinnerDto = TimeWinnerDto.builder()
                .race(timeWinner.getRace())
                .driver(timeWinner.getDriver())
                .model(timeWinner.getModel())
                .dragTime(timeWinner.getDragTime())
                .build();
        return timeWinnerDto;
    }
    public static SpeedWinnerDto toSpeedWinnerDto(SpeedWinner speedWinner) {

        SpeedWinnerDto speedWinnerDto = SpeedWinnerDto.builder()
                .race(speedWinner.getRace())
                .driver(speedWinner.getDriver())
                .model(speedWinner.getModel())
                .speedTrap(speedWinner.getSpeedTrap())
                .build();
        return speedWinnerDto;
    }

    public static Page<DragDto> toListDragDto(Page<Drag> drags) {

        Page<DragDto> dragDtos = drags.map(DragMapper::toDragDto);

        return dragDtos;
    }
    public static List<TimeWinnerDto> toListTimeWinnerDto(List<TimeWinner> timeWinners) {

        List<TimeWinnerDto> timeWinnerDtos = timeWinners.stream().map(tw -> toTimeWinnerDto(tw)).collect(Collectors.toList());

        return timeWinnerDtos;
    }
    public static List<SpeedWinnerDto> toListSpeedWinnerDto(List<SpeedWinner> speedWinners) {

        List<SpeedWinnerDto> speedWinnerDtos = speedWinners.stream().map(sw -> toSpeedWinnerDto(sw)).collect(Collectors.toList());

        return speedWinnerDtos;
    }
}
