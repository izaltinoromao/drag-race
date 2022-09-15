package br.inatel.dragrace.steps;


import br.inatel.dragrace.adapter.dto.CarRequestDto;
import br.inatel.dragrace.config.TestHttpClient;
import br.inatel.dragrace.controller.dto.DragDto;
import br.inatel.dragrace.controller.dto.TimeWinnerDto;
import br.inatel.dragrace.controller.form.DragForm;
import br.inatel.dragrace.model.rest.Error;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DragSteps {

    private static ResponseEntity<DragDto> responseSuccess;
    private static ResponseEntity<Error> responseError;
    private static ResponseEntity<TimeWinnerDto[]> responseListSuccess;

    @Autowired
    private TestHttpClient testHttpClient;

    @When("I successfully create a new drag with car year {int} and car model {string} and driver {string} and drag time {double} and speed trap {double}")
    public void creatingNewDrag(int carYear, String carModel, String driver, Double dragTime, Double speedTrap) {
        CarRequestDto carRequestDto = buildCarRequestDto(carYear, carModel);
        DragForm dragForm = buildDragForm(driver, dragTime, speedTrap);
        responseSuccess = testHttpClient.saveSuccess(carRequestDto, dragForm);
    }

    @When("I unsuccessfully create a new drag with car year {int} and car model {string} and driver {string} and drag time {double} and speed trap {double}")
    public void creatingErrorNewDrag(int carYear, String carModel, String driver, Double dragTime, Double speedTrap) {
        responseSuccess = null;
        CarRequestDto carRequestDto = buildCarRequestDto(carYear, carModel);
        DragForm dragForm = buildDragForm(driver, dragTime, speedTrap);
        responseError = testHttpClient.saveError(carRequestDto, dragForm);
    }

    @When("I successfully read a drag by a valid driver {string}")
    public void readADragByDriver(String driver) {
        responseSuccess = testHttpClient.getSuccses(driver);
    }

    @When("i unsuccessfully read a drag by a invalid driver {string}")
    public void readUnsuccessDragByDriver(String driver){
        responseSuccess = null;
        responseError = testHttpClient.getError(driver);
    }
    @Then("the status code must be {int}")
    public void checkReadStatusCode(int statusCode){
        if (responseSuccess != null)
            assertEquals(statusCode, responseSuccess.getStatusCodeValue());
        else if (responseError != null)
            assertEquals(statusCode, responseError.getStatusCodeValue());
        else if (responseListSuccess != null )
            assertEquals(statusCode, responseListSuccess.getStatusCodeValue());
    }
    @Then("the driver must be equals to {string}")
    public void checkTheDriverResponse(String driver){
        assertEquals(driver, responseSuccess.getBody().getDriver());
    }
    @Then("the carModel must be equals to {string}")
    public void checkTheCarModelResponse(String carModel){
        assertEquals(carModel, responseSuccess.getBody().getModel());
    }
    @Then("the carYear must be equals to {int}")
    public void checkTheCarYearResponse(int carYear){
        assertEquals(carYear, responseSuccess.getBody().getCarYear());
    }
    @Then("the dragTime must be equals to {double}")
    public void checkTheDragTimeResponse(double dragTime){
        assertEquals(dragTime, responseSuccess.getBody().getDragTime());
    }
    @Then("the speedTrap must be equals to {double}")
    public void checkTheSpeedTrapResponse(double speedTrap){
        assertEquals(speedTrap, responseSuccess.getBody().getSpeedTrap());
    }
    @Then("the message must be {string}")
    public void checkTheErrorMessage(String message){
        assertEquals(message, responseError.getBody().getMessage());
    }


    private DragForm buildDragForm(String driver, Double dragTime, Double speedTrap) {
        DragForm dragForm = DragForm.builder().driver(driver).dragTime(dragTime).speedTrap(speedTrap).build();
        return dragForm;
    }

    private CarRequestDto buildCarRequestDto(int carYear, String carModel) {
        CarRequestDto carRequestDto = CarRequestDto.builder().year(carYear).model(carModel).build();

        return carRequestDto;
    }


    @When("I successfully read a list of time winners")
    public void readAListOfTimeWinners() {
        responseError = null;
        responseSuccess = null;
        responseListSuccess = testHttpClient.getListTimeWinnersSuccess();
    }

    @And("the list must contain {int} elements")
    public void theListMustContainElements(int i) {
        assertEquals(i, responseListSuccess.getBody().length);
    }



}
