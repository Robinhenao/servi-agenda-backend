package com.udea.serviagenda.dominio.service;

import com.udea.serviagenda.dominio.service.dto.ServiceData;
import com.udea.serviagenda.dominio.service.dto.ServiceRegistrationData;
import com.udea.serviagenda.dominio.service.dto.ServiceUpdateData;
import com.udea.serviagenda.dominio.service.interfaces.ServiceService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ServiceServiceImp implements ServiceService {
    private static final Logger logger = LoggerFactory.getLogger(ServiceServiceImp.class);


    private ServiceRepository serviceRepository;
    @Autowired
    public ServiceServiceImp(ServiceRepository serviceRepository) {
        this.serviceRepository = serviceRepository;
    }

    @Override
    public ServiceData registerService(ServiceRegistrationData serviceRegistrationData) {
        logger.debug("Attempting to create Service ");
        com.udea.serviagenda.dominio.service.model.Service service = new com.udea.serviagenda.dominio.service.model.Service(
                serviceRegistrationData.description(),
                serviceRegistrationData.price(),
                serviceRegistrationData.employe(),
                serviceRegistrationData.creationDate()
        );
        service = this.serviceRepository.save(service);
        logger.info("Service with ID {} create successfully", service.getIdService());
        return new ServiceData(service);
    }

    @Override
    public ServiceData getService(int idService) {
        logger.debug("Fetching Service with ID: {}", idService);
        com.udea.serviagenda.dominio.service.model.Service service = this.serviceRepository.findByIdService(idService);
        return new ServiceData(service);
    }

    @Override
    public List<ServiceData> getServices() {
        logger.debug("Fetching all Services");
        return this.serviceRepository.findAll().stream().map(ServiceData::new).toList();
    }

    @Override
    public ServiceData updateService(ServiceUpdateData serviceUpdateData) {
        logger.debug("Attempting to update Service ");
        com.udea.serviagenda.dominio.service.model.Service service = this.serviceRepository.findByIdService(serviceUpdateData.idServicio());
        BeanUtils.copyProperties(serviceUpdateData, service, "creationDate");
        service = this.serviceRepository.save(service);
        logger.info("Service with ID {} update successfully", service.getIdService());
        return new ServiceData(service);
    }

    @Override
    public ServiceData deleteService(int idService) {
        logger.debug("Deleting service with ID: {}", idService);
        com.udea.serviagenda.dominio.service.model.Service service = this.serviceRepository.findByIdService(idService);
        service.setIsdelete();
        service = this.serviceRepository.save(service);
        logger.info("Service with ID {} deleted successfully", idService);
        return new ServiceData(service);
    }


}
