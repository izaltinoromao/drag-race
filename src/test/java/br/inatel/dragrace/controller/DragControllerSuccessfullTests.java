package br.inatel.dragrace.controller;

import br.inatel.dragrace.controller.form.DragForm;
import br.inatel.dragrace.utils.JsonUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@ActiveProfiles("test")
public class DragControllerSuccessfullTests {
    @Autowired
    private MockMvc mockMvc;

    @Test
    @Order(6)
    public void givenAnCorretPostRequestNewDrag_whenCallPostMethod_shouldReturn201Code() throws Exception {
        final int year = 2020;
        final String model = "Supra";
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
                .andExpect(status().isCreated()).andReturn();
        JSONObject jsonObject = new JSONObject(mvcResult.getResponse().getContentAsString());
        final String driver = jsonObject.getString("driver");
        assertThat(driver).isEqualTo("Neto");

    }

    @Test
    @Order(1)
    public void givenAnCorrectGetRequestListAllDrags_whenCallGetMethod_shouldReturn200Code() throws Exception {

        final MvcResult mvcResult = mockMvc.perform(
                MockMvcRequestBuilders.get("/drag-race/listdrags"))
                .andExpect(status().isOk()).andReturn();
        JSONObject jsonObject = new JSONObject(mvcResult.getResponse().getContentAsString());
        JSONArray jsonArray = jsonObject.getJSONArray("content");
        JSONObject jsonObject1 = jsonArray.getJSONObject(0);
        assertEquals(1, jsonArray.length());
        assertEquals("Netim", jsonObject1.getString("driver"));
        assertEquals(15.65, jsonObject1.getDouble("dragTime"));
        assertEquals(136.89, jsonObject1.getDouble("speedTrap"));
        assertEquals("BMW", jsonObject1.getString("maker"));
        assertEquals("M3", jsonObject1.getString("model"));
    }

    @Test
    @Order(2)
    public void givenAnCorrectGetRequestDragByDriver_whenCallGetMethod_shouldReturn200code() throws Exception {

        final String driver = "Netim";

        final MvcResult mvcResult = mockMvc.perform(
                MockMvcRequestBuilders.get("/drag-race/drag")
                        .param("driver", driver))
                .andExpect(status().isOk()).andReturn();
        JSONObject jsonObject = new JSONObject(mvcResult.getResponse().getContentAsString());
        assertEquals("Netim", jsonObject.getString("driver"));
        assertEquals(15.65, jsonObject.getDouble("dragTime"));
        assertEquals(136.89, jsonObject.getDouble("speedTrap"));
        assertEquals("BMW", jsonObject.getString("maker"));
        assertEquals("M3", jsonObject.getString("model"));
    }

    @Test
    @Order(5)
    public void givenAnCorrectPostRequestSetWinners_whenCallPostMethod_shouldReturn201Code() throws Exception {
        final MvcResult mvcResult = mockMvc.perform(
                MockMvcRequestBuilders.post("/drag-race/setwinners"))
                .andExpect(status().isOk()).andReturn();
        JSONObject jsonObject = new JSONObject(mvcResult.getResponse().getContentAsString());
        assertEquals("The winners was set successfully", jsonObject.getString("message"));
    }

    @Test
    @Order(3)
    public void givenAnCorrectGetRequestListAllTimeWinners_whenCallGetMethod_shouldReturn200Code() throws Exception {
        final MvcResult mvcResult = mockMvc.perform(
                MockMvcRequestBuilders.get("/drag-race/timewinners"))
                .andExpect(status().isOk()).andReturn();
        JSONArray jsonArray =  new JSONArray(mvcResult.getResponse().getContentAsString());
        JSONObject jsonObject = jsonArray.getJSONObject(0);
        assertEquals(1, jsonArray.length());
        assertEquals(75, jsonObject.getInt("race"));
        assertEquals("Joao", jsonObject.getString("driver"));
        assertEquals("Model 3", jsonObject.getString("model"));
        assertEquals(24.58, jsonObject.getDouble("dragTime"));
    }

    @Test
    @Order(4)
    public void givenAnCorrectGetRequestListAllSpeedWinners_whenCallGetMethod_shouldReturn200Code() throws Exception {
        final MvcResult mvcResult = mockMvc.perform(
                        MockMvcRequestBuilders.get("/drag-race/speedwinners"))
                .andExpect(status().isOk()).andReturn();
        JSONArray jsonArray =  new JSONArray(mvcResult.getResponse().getContentAsString());
        JSONObject jsonObject = jsonArray.getJSONObject(0);
        assertEquals(1, jsonArray.length());
        assertEquals(75, jsonObject.getInt("race"));
        assertEquals("Joao", jsonObject.getString("driver"));
        assertEquals("Model 3", jsonObject.getString("model"));
        assertEquals(145.58, jsonObject.getDouble("speedTrap"));
    }

    @Test
    @Order(7)
    public void givenAnCorrectDeleteRequestResetRace_whenCallDeleteMethod_shoudlReturn204Code() throws Exception {
        final MvcResult mvcResult = mockMvc.perform(
                MockMvcRequestBuilders.delete("/drag-race/resetrace"))
                .andExpect(status().isNoContent()).andReturn();
        JSONObject jsonObject = new JSONObject(mvcResult.getResponse().getContentAsString());
        assertEquals("The race was reseted successfully", jsonObject.get("message"));
    }

}
