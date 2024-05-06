package com.udea.serviagenda.dominio.citatation.interfaces;

import com.udea.serviagenda.dominio.citatation.dto.CitationData;
import com.udea.serviagenda.dominio.citatation.dto.CitationRegistrationData;
import com.udea.serviagenda.dominio.citatation.dto.CitationUpdateData;

import java.util.List;

public interface CitationService {

    public CitationData registerCitation(CitationRegistrationData citationRegistrationData);

    public CitationData updateCitation(CitationUpdateData citationUpdateData);

    public CitationData getCitation(int id);

    public List<CitationData> getCitations();

    public CitationData deleteCitation(int id);


}
