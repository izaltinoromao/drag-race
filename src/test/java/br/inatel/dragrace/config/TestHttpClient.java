package br.inatel.dragrace.config;

import br.inatel.dragrace.adapter.dto.CarRequestDto;
import br.inatel.dragrace.controller.dto.DragDto;
import br.inatel.dragrace.controller.dto.TimeWinnerDto;
import br.inatel.dragrace.controller.form.DragForm;
import br.inatel.dragrace.model.rest.Error;
import io.cucumber.messages.internal.com.google.gson.Gson;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import static io.cucumber.spring.CucumberTestContext.SCOPE_CUCUMBER_GLUE;

@Component
@Scope(SCOPE_CUCUMBER_GLUE)
public class TestHttpClient {

    private final String SERVER_URL = "http://localhost";
    private final String RACE_ENDPOINT = "/drag-race";
    private final RestTemplate restTemplate = new RestTemplate();
    @LocalServerPort
    private int port;

    private String raceEndpoint() {
        return SERVER_URL + ":" + port + RACE_ENDPOINT;
    }

    public ResponseEntity<DragDto>saveSuccess(CarRequestDto carRequestDto, DragForm dragForm){

        int year = carRequestDto.getYear();
        String model = carRequestDto.getModel();

        return restTemplate.postForEntity(String.format("%s?year=%s&model=%s",raceEndpoint() + "/newdrag",year, model), dragForm, DragDto.class);
    }

    public ResponseEntity<Error> saveError(CarRequestDto carRequestDto, DragForm dragForm) {

        try {
            int year = carRequestDto.getYear();
            String model = carRequestDto.getModel();

            restTemplate.postForEntity(String.format("%s?year=%s&model=%s",raceEndpoint() + "/newdrag",year, model), dragForm, DragDto.class);
        }catch (HttpClientErrorException httpClientErrorException){
            Error error = new Gson().fromJson(httpClientErrorException.getResponseBodyAsString(), Error.class);
            return new ResponseEntity<>(error, httpClientErrorException.getStatusCode());
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public ResponseEntity<DragDto> getSuccses(String driver){
        return restTemplate.getForEntity(String.format("%s?driver=%s", raceEndpoint() + "/drag", driver), DragDto.class);
    }


    public ResponseEntity<Error> getError(String driver) {
        try {

            restTemplate.getForEntity(String.format("%s?driver=%s", raceEndpoint() + "/drag", driver), DragDto.class);
        }catch (HttpClientErrorException httpClientErrorException){
            Error error = new Gson().fromJson(httpClientErrorException.getResponseBodyAsString(), Error.class);
            return new ResponseEntity<>(error, httpClientErrorException.getStatusCode());
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public ResponseEntity<TimeWinnerDto[]> getListTimeWinnersSuccess() {
        return restTemplate.getForEntity(raceEndpoint() + "/timewinners", TimeWinnerDto[].class);
    }

}
