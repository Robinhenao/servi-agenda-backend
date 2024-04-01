package com.udea.serviagenda.dominio.service.dto;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.Date;

public record ServiceRegistrationData(
        String description,
        double price,
        Date creationDate
) {
}
