package br.inatel.dragrace.utils;

import br.inatel.dragrace.controller.form.DragForm;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

public class JsonUtils {

    public static String asJsonString(DragForm dragForm) throws JsonProcessingException {


        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String json = ow.writeValueAsString(dragForm);

        return json;
    }
}
