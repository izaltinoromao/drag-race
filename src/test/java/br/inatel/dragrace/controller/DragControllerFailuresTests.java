package br.inatel.dragrace.controller;

import br.inatel.dragrace.controller.form.DragForm;
import br.inatel.dragrace.utils.JsonUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@ActiveProfiles("test2")
@TestPropertySource(locations = "classpath:application-test.properties")
public class DragControllerFailuresTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void givenAnEmptyDriverPostRequestNewDrag_whenCallPostMethod_shouldReturn400Code() throws Exception {
        final int year = 2020;
        final String model = "Supra";
        final DragForm dragForm = DragForm.builder()
                .driver("")
                .dragTime(25.25)
                .speedTrap(125.25)
                .build();

        final MvcResult mvcResult = mockMvc.perform(
                        MockMvcRequestBuilders.post("/drag-race/newdrag")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(JsonUtils.asJsonString(dragForm))
                                .param("year", String.valueOf(year))
                                .param("model", model))
                .andExpect(status().isBadRequest()).andReturn();
        JSONObject jsonObject = new JSONObject(mvcResult.getResponse().getContentAsString());
        assertEquals("BAD_REQUEST", jsonObject.getString("httpStatusCode"));
        assertEquals("driver must not be empty", jsonObject.getString("message"));
    }

    @Test
    public void givenAnEmptyDragTimePostRequestNewDrag_whenCallPostMethod_shouldReturn201Code() throws Exception {
        final int year = 2020;
        final String model = "Supra";
        final DragForm dragForm = DragForm.builder()
                .driver("Neto")
                .speedTrap(125.25)
                .build();

        final MvcResult mvcResult = mockMvc.perform(
                        MockMvcRequestBuilders.post("/drag-race/newdrag")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(JsonUtils.asJsonString(dragForm))
                                .param("year", String.valueOf(year))
                                .param("model", model))
                .andExpect(status().isBadRequest()).andReturn();
        JSONObject jsonObject = new JSONObject(mvcResult.getResponse().getContentAsString());
        assertEquals("BAD_REQUEST", jsonObject.getString("httpStatusCode"));
        assertEquals("dragTime must not be empty", jsonObject.getString("message"));
    }

    @Test
    public void givenAnEmptySpeedTrapPostRequestNewDrag_whenCallPostMethod_shouldReturn201Code() throws Exception {
        final int year = 2020;
        final String model = "Supra";
        final DragForm dragForm = DragForm.builder()
                .driver("Neto")
                .dragTime(25.25)
                .build();

        final MvcResult mvcResult = mockMvc.perform(
                        MockMvcRequestBuilders.post("/drag-race/newdrag")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(JsonUtils.asJsonString(dragForm))
                                .param("year", String.valueOf(year))
                                .param("model", model))
                .andExpect(status().isBadRequest()).andReturn();
        JSONObject jsonObject = new JSONObject(mvcResult.getResponse().getContentAsString());
        assertEquals("BAD_REQUEST", jsonObject.getString("httpStatusCode"));
        assertEquals("speedTrap must not be empty", jsonObject.getString("message"));
    }

    @Test
    public void givenAnInvalidCarModelTrapPostRequestNewDrag_whenCallPostMethod_shouldReturn404Code() throws Exception {
        final int year = 2020;
        final String model = "sdvarsd";
        final DragForm dragForm = DragForm.builder()
                .driver("Neto")
                .dragTime(25.25)
                .speedTrap(125.25)
                .build();

        final MvcResult mvcResult = mockMvc.perform(
                        MockMvcRequestBuilders.post("/drag-race/newdrag")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(JsonUtils.asJsonString(dragForm))
                                .param("year", String.valueOf(year))
                                .param("model", model))
                .andExpect(status().isNotFound()).andReturn();
        JSONObject jsonObject = new JSONObject(mvcResult.getResponse().getContentAsString());
        assertEquals("NOT_FOUND", jsonObject.getString("httpStatusCode"));
        assertEquals("Car model: " + model + " and year: " + year + " not found at CarDataApi", jsonObject.getString("message"));
        Thread.sleep(1000);
    }

    @Test
    public void givenAnInvalidYearTrapPostRequestNewDrag_whenCallPostMethod_shouldReturn404Code() throws Exception {
        final int year = 2050;
        final String model = "sdvarsd";
        final DragForm dragForm = DragForm.builder()
                .driver("Neto")
                .dragTime(25.25)
                .speedTrap(125.25)
                .build();

        final MvcResult mvcResult = mockMvc.perform(
                        MockMvcRequestBuilders.post("/drag-race/newdrag")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(JsonUtils.asJsonString(dragForm))
                                .param("year", String.valueOf(year))
                                .param("model", model))
                .andExpect(status().isNotFound()).andReturn();
        JSONObject jsonObject = new JSONObject(mvcResult.getResponse().getContentAsString());
        assertEquals("NOT_FOUND", jsonObject.getString("httpStatusCode"));
        assertEquals("Car model: " + model + " and year: " + year + " not found at CarDataApi", jsonObject.getString("message"));
        Thread.sleep(1000);
    }

    @Test
    public void givenAnEmptyDragListGetRequestListAllDrags_whenCallGetMethod_shouldReturn200Code() throws Exception {

        final MvcResult mvcResult = mockMvc.perform(
                        MockMvcRequestBuilders.get("/drag-race/listdrags"))
                .andExpect(status().isOk()).andReturn();
        JSONObject jsonObject = new JSONObject(mvcResult.getResponse().getContentAsString());
        JSONArray jsonArray = jsonObject.getJSONArray("content");
        assertEquals(true, jsonObject.getBoolean("empty"));
        assertEquals(0, jsonObject.getInt("totalElements"));
        assertEquals(0, jsonArray.length());

    }

    @Test
    public void givenAnInvalidDriverGetRequestDragByDriver_whenCallGetMethod_shouldReturn404code() throws Exception {

        final String driver = "Netim";

        final MvcResult mvcResult = mockMvc.perform(
                        MockMvcRequestBuilders.get("/drag-race/drag")
                                .param("driver", driver))
                .andExpect(status().isNotFound()).andReturn();
        JSONObject jsonObject = new JSONObject(mvcResult.getResponse().getContentAsString());
        assertEquals("NOT_FOUND", jsonObject.getString("httpStatusCode"));
        assertEquals("No driver " + driver + " was found at Data Base", jsonObject.getString("message"));
    }

    @Test
    public void givenNoDriverParameterGetRequestDragByDriver_whenCallGetMethod_shouldReturn404code() throws Exception {
        final MvcResult mvcResult = mockMvc.perform(
                        MockMvcRequestBuilders.get("/drag-race/drag"))
                .andExpect(status().isBadRequest()).andReturn();
        JSONObject jsonObject = new JSONObject(mvcResult.getResponse().getContentAsString());
        assertEquals("BAD_REQUEST", jsonObject.getString("httpStatusCode"));
        assertEquals("driver parameter is required", jsonObject.getString("message"));
    }

    @Test
    public void givenAnEmptyDragListPostRequestSetWinners_whenCallPostMethod_shouldReturn404Code() throws Exception {
        final MvcResult mvcResult = mockMvc.perform(
                        MockMvcRequestBuilders.post("/drag-race/setwinners"))
                .andExpect(status().isNotFound()).andReturn();
        JSONObject jsonObject = new JSONObject(mvcResult.getResponse().getContentAsString());
        assertEquals("NOT_FOUND", jsonObject.getString("httpStatusCode"));
        assertEquals("there's no drags at the race yet", jsonObject.getString("message"));
    }

    @Test
    public void givenAnEmptyTimeWinnersListGetRequestListAllTimeWinners_whenCallGetMethod_shouldReturn200Code() throws Exception {
        final MvcResult mvcResult = mockMvc.perform(
                        MockMvcRequestBuilders.get("/drag-race/timewinners"))
                .andExpect(status().isOk()).andReturn();
        JSONArray jsonArray =  new JSONArray(mvcResult.getResponse().getContentAsString());
        assertEquals(0, jsonArray.length());
    }

    @Test
    public void givenAnEmptySpeedWinnersListGetRequestListAllTimeWinners_whenCallGetMethod_shouldReturn200Code() throws Exception {
        final MvcResult mvcResult = mockMvc.perform(
                        MockMvcRequestBuilders.get("/drag-race/speedwinners"))
                .andExpect(status().isOk()).andReturn();
        JSONArray jsonArray =  new JSONArray(mvcResult.getResponse().getContentAsString());
        assertEquals(0, jsonArray.length());
    }

    @Test
    public void givenAnEmptyDragListDeleteRequestResetRace_whenCallDeleteMethod_shoudlReturn400Code() throws Exception {
        final MvcResult mvcResult = mockMvc.perform(
                        MockMvcRequestBuilders.delete("/drag-race/resetrace"))
                .andExpect(status().isBadRequest()).andReturn();
        JSONObject jsonObject = new JSONObject(mvcResult.getResponse().getContentAsString());
        assertEquals("BAD_REQUEST", jsonObject.getString("httpStatusCode"));
        assertEquals("The race is already reseted", jsonObject.getString("message"));
    }
}
