package com.udea.serviagenda.controller;


import com.udea.serviagenda.dominio.service.ServiceServiceImp;
import com.udea.serviagenda.dominio.service.dto.ServiceData;
import com.udea.serviagenda.dominio.service.dto.ServiceRegistrationData;
import com.udea.serviagenda.dominio.service.dto.ServiceUpdateData;
import com.udea.serviagenda.dominio.service.interfaces.ServiceService;
import com.udea.serviagenda.dominio.user.dto.UserData;
import com.udea.serviagenda.dominio.user.dto.UserRegistrationClientData;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/services")
@CrossOrigin("*")
public class ServiceController {

    @Autowired
    private ServiceService serviceService;

    public ServiceController(ServiceService serviceService) {
        this.serviceService = serviceService;
    }

    @PostMapping("/register")
    public ResponseEntity<ServiceData> registerService(@RequestBody ServiceRegistrationData serviceRegistrationData) {
        ServiceData serviceData = serviceService.registerService(serviceRegistrationData);
        return new ResponseEntity<>(serviceData, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ServiceData> getService(@PathVariable int id) {
        ServiceData serviceData = serviceService.getService(id);
        if (serviceData != null) {
            return new ResponseEntity<>(serviceData, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public ResponseEntity<List<ServiceData>> getServices() {
        List<ServiceData> services = serviceService.getServices();
        return new ResponseEntity<>(services, HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<ServiceData> updateService(@RequestBody ServiceUpdateData serviceUpdateData) {
        ServiceData updatedService = serviceService.updateService(serviceUpdateData);
        if (updatedService != null) {
            return new ResponseEntity<>(updatedService, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteService(@PathVariable int id) {
        serviceService.deleteService(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
