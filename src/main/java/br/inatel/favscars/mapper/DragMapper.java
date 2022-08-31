package br.inatel.favscars.mapper;

import br.inatel.favscars.controller.dto.DragDto;
import br.inatel.favscars.model.Drag;

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


    public static List<DragDto> toListDragDto(List<Drag> drags) {

        List<DragDto> dragDtos = drags.stream().map(d -> toDragDto(d)).collect(Collectors.toList());

        return dragDtos;
    }
}
