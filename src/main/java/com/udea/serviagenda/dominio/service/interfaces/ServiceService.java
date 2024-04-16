package com.udea.serviagenda.dominio.service.interfaces;

import com.udea.serviagenda.dominio.service.dto.ServiceData;
import com.udea.serviagenda.dominio.service.dto.ServiceRegistrationData;
import com.udea.serviagenda.dominio.service.dto.ServiceUpdateData;
import com.udea.serviagenda.dominio.service.model.Service;

import java.util.List;

public interface ServiceService {

    ServiceData registerService(ServiceRegistrationData serviceRegistrationData);

    ServiceData getService(int idService);

    List<ServiceData> getServices();

    ServiceData updateService(ServiceUpdateData serviceUpdateData);

    void deleteService(int idService);

}
