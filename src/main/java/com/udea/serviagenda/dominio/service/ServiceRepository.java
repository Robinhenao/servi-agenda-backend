package com.udea.serviagenda.dominio.service;


import com.udea.serviagenda.dominio.service.model.Service;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServiceRepository extends JpaRepository<Service, Long> {
    Service findByIdService(int idService);
}
