package com.udea.serviagenda.dominio.citatation.dto;

import com.udea.serviagenda.dominio.service.model.Service;
import com.udea.serviagenda.dominio.user.model.User;

import java.util.Date;

public record CitationUpdateData(
        int id,
        Date date,
        User client,
        Service service
) {
}
