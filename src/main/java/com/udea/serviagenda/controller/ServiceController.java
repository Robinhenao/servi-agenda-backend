package com.udea.serviagenda.controller;


import com.udea.serviagenda.dominio.service.ServiceServiceImp;
import com.udea.serviagenda.dominio.service.dto.ServiceData;
import com.udea.serviagenda.dominio.service.dto.ServiceRegistrationData;
import com.udea.serviagenda.dominio.service.interfaces.ServiceService;
import com.udea.serviagenda.dominio.user.dto.UserData;
import com.udea.serviagenda.dominio.user.dto.UserRegistrationClientData;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/services")
@CrossOrigin("*")
public class ServiceController {

    private ServiceService serviceService;

    public ServiceController(ServiceService serviceService) {
        this.serviceService = serviceService;
    }

    @PostMapping
    public ResponseEntity<ServiceData> registerService(@RequestBody @Valid ServiceRegistrationData serviceRegistrationData) {
        ServiceData serviceData = this.serviceService.registerService(serviceRegistrationData);
        return ResponseEntity.created(null).body(serviceData);
    }

}
