package com.udea.serviagenda;

import com.udea.serviagenda.dominio.service.dto.ServiceData;
import com.udea.serviagenda.dominio.service.dto.ServiceRegistrationData;
import com.udea.serviagenda.dominio.service.dto.ServiceUpdateData;
import com.udea.serviagenda.dominio.service.interfaces.ServiceService;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;


public class ServiceSteps {

    @Autowired
    private ServiceService serviceService;
    private ServiceRegistrationData serviceRegistrationData;
    private ServiceData registeredService;
    private ServiceRegistrationData registrationData;

    private int serviceId;
    private ServiceData fetchedService;
    private ServiceData deletedService;


    //registro service

    @Given("que deseo registrar con descripcion {string}, precio {double}, empleado {long} y fecha de creacion {string}")
    public void queDeseoRegistrarConDescripcionPrecioEmpleadoYFechaDeCreacion(String descripcion, double precio, Long empleado, String fechaCreacion) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        registrationData = new ServiceRegistrationData(descripcion, precio, empleado, dateFormat.parse(fechaCreacion));
    }

    @When("se registra el servicio")
    public void seRegistraElServicio() {
        registeredService = serviceService.registerService(registrationData);
    }

    @Then("el sistema muestra el servicio registrado con éxito")
    public void elSistemaMuestraElServicioRegistradoConÉxito() {
        assertNotNull(registeredService);
        assertEquals(registrationData.description(), registeredService.description());
        assertEquals(registrationData.price(), registeredService.price());
        assertEquals(registrationData.creationDate(), registeredService.creationDate());
    }

    //buscar por id

    @Given("que deseo obtener el servicio con ID {int}")
    public void queDeseoObtenerElServicioConID(int id) {
        serviceId = id;
    }

    @When("se busca el servicio por ID")
    public void seBuscaElServicioPorID() {
        fetchedService = serviceService.getService(serviceId);
    }

    @Then("el sistema muestra la información del servicio con ID {int}")
    public void elSistemaMuestraLaInformaciónDelServicioConID(int id) {
        assertNotNull(fetchedService);
        assertEquals(id, fetchedService.idService());
    }

    //actualizar por id

    private ServiceUpdateData updateData;
    private ServiceData updatedService;
    @Given("que deseo actualizar el servicio con ID {int} con nueva descripción {string}")
    public void queDeseoActualizarElServicioConIDConNuevaDescripción(int id, String nuevaDescripcion) {
        updateData = new ServiceUpdateData(id, nuevaDescripcion,10000,1L);
    }

    @When("se actualiza el servicio")
    public void seActualizaElServicio() {
        updatedService = serviceService.updateService(updateData);
    }

    @Then("el sistema muestra el servicio actualizado correctamente")
    public void elSistemaMuestraElServicioActualizadoCorrectamente() {
        assertNotNull(updatedService);
        assertEquals(updateData.idServicio(), updatedService.idService());
        assertEquals(updateData.description(), updatedService.description());
    }


    //eliminar

    @Given("que deseo eliminar el servicio con ID {int}")
    public void queDeseoEliminarElServicioConID(int id) {
        serviceId = id;
    }

    @When("se elimina el servicio")
    public void seEliminaElServicio() {
        deletedService = serviceService.deleteService(serviceId);
    }

    @Then("el servicio con ID {int} es marcado como eliminado")
    public void elServicioConIDEsMarcadoComoEliminado(int id) {
        assertNotNull(deletedService);
        assertTrue(deletedService.isdelete());
    }
}
