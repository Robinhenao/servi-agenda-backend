package com.udea.serviagenda.controller;

import com.udea.serviagenda.dominio.user.dto.UserData;
import com.udea.serviagenda.dominio.user.dto.UserRegistrationData;
import com.udea.serviagenda.dominio.user.dto.UserUpdateData;
import com.udea.serviagenda.dominio.user.interfaces.EmployeService;
import com.udea.serviagenda.dominio.user.interfaces.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employes")
@CrossOrigin("*")
public class EmployeController {

    private EmployeService employeService;

    public EmployeController(EmployeService employeService){
        this.employeService = employeService;
    }

    @PostMapping("/register")
    public ResponseEntity<UserData> registerUser(@RequestBody @Valid UserRegistrationData userRegistrationData){
        UserData userData = this.employeService.registerUserEmploye(userRegistrationData);
        return ResponseEntity.status(HttpStatus.CREATED).body(userData);
    }

    @PutMapping("/update")
    public ResponseEntity<UserData> updateEmployee(@RequestBody UserUpdateData userUpdateData) {
        UserData userData = this.employeService.updateUserEmploye(userUpdateData);
        if (userData != null) {
            return ResponseEntity.ok(userData);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserData> getEmployee(@PathVariable int id) {
        UserData userData = this.employeService.getUserEmploye(id);
        if (userData != null) {
            return ResponseEntity.ok(userData);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<UserData>> getAllEmployees() {
        List<UserData> userDatas = this.employeService.getUserEmployes();
        return ResponseEntity.ok(userDatas);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<UserData> deleteEmployee(@PathVariable int id) {
        UserData userData = this.employeService.deleteUserEmploye(id);
        if (userData != null) {
            return ResponseEntity.ok(userData);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
