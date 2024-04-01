package com.udea.serviagenda.controller;

import com.udea.serviagenda.dominio.user.dto.UserData;
import com.udea.serviagenda.dominio.user.dto.UserRegistrationData;
import com.udea.serviagenda.dominio.user.interfaces.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/employes")
@CrossOrigin("*")
public class EmployeController {

    private UserService userService;

    public EmployeController(UserService userService){
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<UserData> registerUser(@RequestBody @Valid UserRegistrationData userRegistrationData){
        UserData userData = this.userService.registerUserEmploye(userRegistrationData);
        return ResponseEntity.created(null).body(userData);
    }
}
