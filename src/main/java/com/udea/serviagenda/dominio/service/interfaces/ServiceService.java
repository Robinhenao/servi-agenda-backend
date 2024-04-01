package com.udea.serviagenda.dominio.service.interfaces;

import com.udea.serviagenda.dominio.service.dto.ServiceData;
import com.udea.serviagenda.dominio.service.dto.ServiceRegistrationData;
import com.udea.serviagenda.dominio.service.model.Service;

public interface ServiceService {

    public ServiceData registerService(ServiceRegistrationData serviceRegistrationData);

}
