package com.udea.serviagenda.dominio.citatation.dto;

import com.udea.serviagenda.dominio.citatation.model.Citation;
import com.udea.serviagenda.dominio.service.model.Service;
import com.udea.serviagenda.dominio.user.model.User;

import java.util.Date;

public record CitationData(
        int id,
        Date date,
        User client,
        Service service,
        Date dateCreation
) {
    public CitationData(Citation citation) {
        this(
                citation.getId(),
                citation.getDate(),
                citation.getClient(),
                citation.getService(),
                citation.getDateCreation());
    }
}
