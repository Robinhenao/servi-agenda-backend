package com.udea.serviagenda.dominio.citatation;

import com.udea.serviagenda.dominio.citatation.dto.CitationData;
import com.udea.serviagenda.dominio.citatation.dto.CitationRegistrationData;
import com.udea.serviagenda.dominio.citatation.dto.CitationUpdateData;
import com.udea.serviagenda.dominio.citatation.interfaces.CitationService;
import com.udea.serviagenda.dominio.citatation.model.Citation;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class CitationServiceImpl implements CitationService {

    private CitationRepository citationRepository;

    public CitationServiceImpl(CitationRepository citationRepository) {
        this.citationRepository = citationRepository;
    }

    @Override
    public CitationData registerCitation(CitationRegistrationData citationRegistrationData) {
        Citation citation = new Citation(
           citationRegistrationData.date(),
                citationRegistrationData.client(),
                citationRegistrationData.service(),
                citationRegistrationData.dateCreation()
        );
        citation= this.citationRepository.save(citation);
        return new CitationData(citation);
    }

    @Override
    public CitationData updateCitation(CitationUpdateData citationUpdateData) {
        Citation citation = citationRepository.findById(citationUpdateData.id());
        BeanUtils.copyProperties(citationUpdateData, citation, "id");
        return new CitationData(citation);
    }

    @Override
    public CitationData getCitation(int id) {
        Citation citation= citationRepository.findById(id);
        return new CitationData(citation);
    }

    @Override
    public List<CitationData> getCitations() {
        return this.citationRepository.findAll().stream().map(CitationData::new).toList();
    }

    @Override
    public CitationData deleteCitation(int id) {
        Citation citation = citationRepository.findById(id);
        citation.setIsAvailable(false);
        citation= this.citationRepository.save(citation);
        return new CitationData(citation);
    }
}
