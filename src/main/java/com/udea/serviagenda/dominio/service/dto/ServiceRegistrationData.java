package com.udea.serviagenda.dominio.service.dto;

import com.udea.serviagenda.dominio.user.model.User;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.Date;

public record ServiceRegistrationData(
        @NotNull
        @Size(min = 1, max = 100)
        String description,
        @DecimalMin(value = "0.0", inclusive = false)
        double price,
        @NotNull
        Long employe,
        @NotNull
        Date creationDate
) {
}
