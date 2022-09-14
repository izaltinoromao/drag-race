package br.inatel.dragrace.adapter;

import br.inatel.dragrace.adapter.dto.CarDto;
import br.inatel.dragrace.adapter.dto.CarRequestDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

/**
 * This class is responsible to connect with the external API and bring important information
 * @author izaltino.
 * @since 09/09/2022
 */
@Service
public class CarDataAdapter {

    @Value("${cardata.url}")
    private String URL_CARDATA;

    @Value("${cardata.host}")
    private String HOST_CARDATA;

    @Value("${cardata.token}")
    private String TOKEN_CARDATA;


    /**
     * Method responsible to search into the CarDataAPI data from a car passed by parameters
     * @param carRequestDto
     * @return The carDto from the given parameters
     */
    public CarDto getCar(CarRequestDto carRequestDto) {

        int year = carRequestDto.getYear();
        String model = carRequestDto.getModel();

        List<CarDto> carsDtos = WebClient.create(URL_CARDATA)
                .get()
                .uri(uriBuilder -> uriBuilder
                        .path("/cars")
                        .queryParam("limit", "50")
                        .queryParam("pages", "0")
                        .queryParam("model", "{model}")
                        .queryParam("year", "{year}")
                        .build(model, year))
                .header("X-RapidAPI-KEY", TOKEN_CARDATA)
                .header("X-RapidAPI-Host", HOST_CARDATA)
                .retrieve()
                .bodyToFlux(CarDto.class)
                .take(1)
                .collectList()
                .block();

        CarDto carDto = new CarDto();
        if (carsDtos.isEmpty()) {
            carDto = null;
        } else {
            carDto = carsDtos.get(0);
        }

        return carDto;

    }
}