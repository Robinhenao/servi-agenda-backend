package com.udea.serviagenda.controller;

import com.udea.serviagenda.dominio.user.dto.UserData;
import com.udea.serviagenda.dominio.user.dto.UserRegistrationClientData;
import com.udea.serviagenda.dominio.user.dto.UserRegistrationData;
import com.udea.serviagenda.dominio.user.dto.UserUpdateData;
import com.udea.serviagenda.dominio.user.interfaces.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clients")
@CrossOrigin("*")
public class ClientController {

    private UserService userService;

    public ClientController(UserService userService){
        this.userService = userService;
    }



    @PostMapping("/register")
    public ResponseEntity<UserData> registerUserClient(@RequestBody UserRegistrationClientData userRegistrationClientData) {
        UserData userData = this.userService.registerUserClient(userRegistrationClientData);
        return ResponseEntity.status(HttpStatus.CREATED).body(userData);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserData> getUserClient(@PathVariable int id) {
        UserData userData = this.userService.getUserClient(id);
        if (userData != null) {
            return ResponseEntity.ok(userData);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<UserData>> getUserClients() {
        List<UserData> userDatas = this.userService.getUserClients();
        return ResponseEntity.ok(userDatas);
    }

    @PutMapping
    public ResponseEntity<UserData> updateUserClient(@RequestBody UserUpdateData userUpdateData) {
        UserData userData = this.userService.updateUserClient(userUpdateData);
        return ResponseEntity.ok(userData);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<UserData> deleteUserClient(@PathVariable int id) {
        UserData userData = this.userService.deleteUserClient(id);
        return ResponseEntity.ok(userData);
    }


}
