package com.udea.serviagenda.dominio.service;

import com.udea.serviagenda.dominio.service.dto.ServiceData;
import com.udea.serviagenda.dominio.service.dto.ServiceRegistrationData;
import com.udea.serviagenda.dominio.service.interfaces.ServiceService;
import com.udea.serviagenda.dominio.service.model.Service;


@org.springframework.stereotype.Service
public class ServiceServiceImp implements ServiceService {

    private ServiceRepository serviceRepository;

    public ServiceServiceImp(ServiceRepository serviceRepository) {
        this.serviceRepository = serviceRepository;
    }

    @Override
    public ServiceData registerService(ServiceRegistrationData serviceRegistrationData) {
        Service service = new Service(
            serviceRegistrationData.description(),
            serviceRegistrationData.price(),
            serviceRegistrationData.creationDate()
        );
        service = this.serviceRepository.save(service);
        return new ServiceData(service);
    }
}
