package com.udea.serviagenda.dominio.user.dto;

import com.udea.serviagenda.dominio.user.model.Role;
import com.udea.serviagenda.dominio.user.model.User;

public record UserData(
        String name,
        String lastName,
        int userId,
        String phone,
        String email,
        Role role
) {
    public UserData(User user){
        this(
                user.getName(),
                user.getLastName(),
                user.getUserId(),
                user.getPhone(),
                user.getEmail(),
                user.getRole());
    }
}
