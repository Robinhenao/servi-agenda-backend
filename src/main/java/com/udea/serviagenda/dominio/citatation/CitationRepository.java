package com.udea.serviagenda.dominio.citatation;

import com.udea.serviagenda.dominio.citatation.model.Citation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CitationRepository extends JpaRepository<Citation, Long> {

    Citation findById(int id);

    void deleteById(int id);
}
