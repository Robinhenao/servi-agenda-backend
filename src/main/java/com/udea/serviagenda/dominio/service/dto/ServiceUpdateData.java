package com.udea.serviagenda.dominio.service.dto;

import com.udea.serviagenda.dominio.user.model.User;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.Date;

public record ServiceUpdateData(
        int idServicio,
        @NotNull
        @Size(min = 1, max = 100)
        String description,
        @DecimalMin(value = "0.0", inclusive = false)
        double price,
        @NotNull
        Long employe

) {
}
