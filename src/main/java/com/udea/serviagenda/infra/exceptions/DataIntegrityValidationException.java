package com.udea.serviagenda.infra.exceptions;

import lombok.Getter;

import java.util.List;
@Getter
public class DataIntegrityValidationException extends RuntimeException {
    private List<CustomValidationException> validationExceptions;
    public DataIntegrityValidationException(List<CustomValidationException> exceptions) {
        this.validationExceptions = exceptions;
    }
}
