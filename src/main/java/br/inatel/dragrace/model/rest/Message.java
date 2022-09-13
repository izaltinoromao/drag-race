package br.inatel.dragrace.model.rest;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

/**
 * @author izaltino.
 * @since 09/09/2022
 */
@Data
@AllArgsConstructor
@Builder
public class Message {

    private String message;
}
