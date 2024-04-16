package com.udea.serviagenda.dominio.service;

import com.udea.serviagenda.dominio.service.dto.ServiceData;
import com.udea.serviagenda.dominio.service.dto.ServiceRegistrationData;
import com.udea.serviagenda.dominio.service.dto.ServiceUpdateData;
import com.udea.serviagenda.dominio.service.interfaces.ServiceService;
import com.udea.serviagenda.dominio.service.model.Service;
import com.udea.serviagenda.dominio.user.dto.UserData;
import com.udea.serviagenda.dominio.user.model.User;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.stream.Collectors;


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
            serviceRegistrationData.employe(),
            serviceRegistrationData.creationDate()
        );
        service = this.serviceRepository.save(service);
        return new ServiceData(service);
    }

    @Override
    public ServiceData getService(int idService) {
        Service service = serviceRepository.findByIdService(idService);
        return new ServiceData(service);
    }

    @Override
    public List<ServiceData> getServices() {
        return this.serviceRepository.findAll().stream().map(ServiceData::new).toList();
    }

    @Override
    public ServiceData updateService(ServiceUpdateData serviceUpdateData) {
        Service service = serviceRepository.findByIdService(serviceUpdateData.idServicio());
        BeanUtils.copyProperties(serviceUpdateData, service, "creationDate");
        return new ServiceData(service);
    }

    @Override
    public void deleteService(int idService) {
        Service service = serviceRepository.findByIdService(idService);
        service.setIsdelete();
        serviceRepository.save(service);
    }


}
