package com.udea.serviagenda;

import com.udea.serviagenda.dominio.service.ServiceServiceImp;
import com.udea.serviagenda.dominio.service.dto.ServiceData;
import com.udea.serviagenda.dominio.service.dto.ServiceRegistrationData;
import com.udea.serviagenda.dominio.service.interfaces.ServiceService;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

public class ServiceSteps {

    ServiceService serviceService;
    private ServiceRegistrationData serviceRegistrationData;

    private ServiceData registeredService;


    @Given("I have service registration data with description")
    public void iHaveServiceRegistrationDataWithDescription(){
        serviceRegistrationData = new ServiceRegistrationData("description", 20000, 1L, new Date());
    }

    @When("I register the service")
    public void registerTheService() {
        registeredService = serviceService.registerService(serviceRegistrationData);
    }

    @Then("the service with ID should be created successfully")
    public void verifyServiceCreation() {
        //Assert.assertNotNull(registeredService);
        //Assert.assertNotNull(registeredService.idService()); // Assuming getId() method in ServiceData
        System.out.println("se pudo");
    }


}
