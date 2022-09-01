package br.inatel.dragrace.model.rest;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class Message {

    private String message;
}
