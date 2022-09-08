package br.inatel.dragrace.controller;

import br.inatel.dragrace.controller.form.DragForm;
import br.inatel.dragrace.utils.JsonUtils;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class DragControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
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
        final String id = jsonObject.getString("driver");
        assertThat(id).isEqualTo("Neto");

    }
}
