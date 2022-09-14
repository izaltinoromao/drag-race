package br.inatel.dragrace.utils;

import br.inatel.dragrace.controller.form.DragForm;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

/**
 * Util class for json manipulation
 * @author izaltino.
 * @since 09/09/2022
 */
public class JsonUtils {


    /**
     * Convert an object to a Json string object
     * @param dragForm
     * @return json String
     * @throws JsonProcessingException
     */
    public static String asJsonString(DragForm dragForm) throws JsonProcessingException {


        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String json = ow.writeValueAsString(dragForm);

        return json;
    }
}
