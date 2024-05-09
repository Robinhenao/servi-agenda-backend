package com.udea.serviagenda.dominio.citatation;

import com.udea.serviagenda.dominio.citatation.dto.CitationData;
import com.udea.serviagenda.dominio.citatation.dto.CitationRegistrationData;
import com.udea.serviagenda.dominio.citatation.dto.CitationUpdateData;
import com.udea.serviagenda.dominio.citatation.interfaces.CitationService;
import com.udea.serviagenda.dominio.citatation.model.Citation;

import com.udea.serviagenda.dominio.service.ServiceServiceImp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class CitationServiceImpl implements CitationService {

    private static final Logger logger = LoggerFactory.getLogger(CitationServiceImpl.class);
    private CitationRepository citationRepository;

    public CitationServiceImpl(CitationRepository citationRepository) {
        this.citationRepository = citationRepository;
    }

    @Override
    public CitationData registerCitation(CitationRegistrationData citationRegistrationData) {
        logger.debug("Attempting to create citation");
        Citation citation = new Citation(
                citationRegistrationData.date(),
                citationRegistrationData.client(),
                citationRegistrationData.service(),
                citationRegistrationData.dateCreation()
        );
        citation= this.citationRepository.save(citation);
        logger.info("citation with ID {} create successfully",citation.getId());
        return new CitationData(citation);
    }

    @Override
    public CitationData updateCitation(CitationUpdateData citationUpdateData) {
        logger.debug("Attempting to update citation ");
        Citation citation = this.citationRepository.findById(citationUpdateData.id());
        BeanUtils.copyProperties(citationUpdateData, citation, "id");
        citation = this.citationRepository.save(citation);
        logger.info("Citation with ID {} update successfully", citation.getId());
        return new CitationData(citation);
    }

    @Override
    public CitationData getCitation(int id) {
        logger.debug("Fetching Service with ID: {}", id);
        Citation citation= this.citationRepository.findById(id);
        return new CitationData(citation);
    }

    @Override
    public List<CitationData> getCitations() {
        logger.debug("Fetching all Citations");
        return this.citationRepository.findAll().stream().map(CitationData::new).toList();
    }

    @Override
    public CitationData deleteCitation(int id) {
        logger.debug("Deleting citation with ID: {}", id);
        Citation citation = citationRepository.findById(id);
        citation.setIsAvailable(false);
        citation= this.citationRepository.save(citation);
        logger.info("Citation with ID {} deleted successfully", id);
        return new CitationData(citation);
    }
}
