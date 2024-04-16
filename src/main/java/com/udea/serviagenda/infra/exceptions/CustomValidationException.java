package com.udea.serviagenda.infra.exceptions;

import jakarta.validation.ValidationException;
import lombok.Getter;

@Getter
public class CustomValidationException extends ValidationException {
    private String field;

    public CustomValidationException(String field, String message){
        super(message);
        this.field = field;
    }
}
