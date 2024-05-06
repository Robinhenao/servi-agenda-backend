package com.udea.serviagenda.controller;

import com.udea.serviagenda.dominio.citatation.dto.CitationData;
import com.udea.serviagenda.dominio.citatation.dto.CitationRegistrationData;
import com.udea.serviagenda.dominio.citatation.dto.CitationUpdateData;
import com.udea.serviagenda.dominio.citatation.interfaces.CitationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/citation")
@CrossOrigin("*")
public class CitationController {

    private CitationService citationService;

    public CitationController(CitationService citationService) {
        this.citationService = citationService;
    }

    @GetMapping
    public ResponseEntity<List<CitationData>> getCitations() {
        List<CitationData> citations = citationService.getCitations();
        return ResponseEntity.ok(citations);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CitationData> getCitation(@PathVariable int id) {
        CitationData citation = citationService.getCitation(id);
        if (citation == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(citation);
    }

    // Register a new citation
    @PostMapping
    public ResponseEntity<CitationData> registerCitation(@RequestBody CitationRegistrationData citationRegistrationData) {
        CitationData CitationData = citationService.registerCitation(citationRegistrationData);
        return ResponseEntity.ok(CitationData);
    }

    // Update an existing citation
    @PutMapping("/{id}")
    public ResponseEntity<CitationData> updateCitation(@RequestBody CitationUpdateData citationUpdateData) {
        CitationData updatedCitation = citationService.updateCitation(citationUpdateData);
        return ResponseEntity.ok(updatedCitation);
    }

    // Delete a citation (soft delete)
    @DeleteMapping("/{id}")
    public ResponseEntity<CitationData> deleteCitation(@PathVariable int id) {
        CitationData deletedCitation = citationService.deleteCitation(id);
        return ResponseEntity.ok(deletedCitation);
    }


}
