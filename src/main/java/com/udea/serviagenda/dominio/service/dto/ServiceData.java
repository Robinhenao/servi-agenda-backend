package com.udea.serviagenda.dominio.service.dto;

import com.udea.serviagenda.dominio.service.model.Service;

import java.util.Date;

public record ServiceData(
        int idService,
        String description,
        double price,
        Date creationDate) {
    public ServiceData(Service service) {
        this(
                service.getIdService(),
                service.getDescription(),
                service.getPrice(),
                service.getCreationDate());
    }
}
