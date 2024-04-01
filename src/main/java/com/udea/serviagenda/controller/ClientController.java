package com.udea.serviagenda.controller;

import com.udea.serviagenda.dominio.user.dto.UserData;
import com.udea.serviagenda.dominio.user.dto.UserRegistrationClientData;
import com.udea.serviagenda.dominio.user.dto.UserRegistrationData;
import com.udea.serviagenda.dominio.user.interfaces.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/clients")
@CrossOrigin("*")
public class ClientController {

    private UserService userService;

    public ClientController(UserService userService){
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<UserData> registerUser(@RequestBody @Valid UserRegistrationClientData userRegistrationClientData){
        UserData userData = this.userService.registerUserClient(userRegistrationClientData);
        return ResponseEntity.created(null).body(userData);
    }
}
