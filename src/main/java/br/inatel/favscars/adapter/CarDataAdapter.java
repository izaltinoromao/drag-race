package br.inatel.favscars.adapter;

import br.inatel.favscars.adapter.dto.CarDto;
import br.inatel.favscars.adapter.dto.CarRequestDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Service
public class CarDataAdapter {

    @Value("${cardata.url}")
    private String URL_CARDATA;

    @Value("${cardata.host}")
    private String HOST_CARDATA;

    @Value("${cardata.token}")
    private String TOKEN_CARDATA;


    public List<String> getAllMakers() {

        List<String> makersDtos = WebClient.create(URL_CARDATA)
                .get()
                .uri("/cars/makes")
                .header("X-RapidAPI-KEY", TOKEN_CARDATA)
                .header("X-RapidAPI-Host", HOST_CARDATA)
                .retrieve()
                .bodyToFlux(String.class)
                .collectList()
                .block();

        return makersDtos;

    }

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
        if(carsDtos.isEmpty())
        {
            carDto = null;
        }else{
            carDto = carsDtos.get(0);
        }

        return carDto;

    }
}