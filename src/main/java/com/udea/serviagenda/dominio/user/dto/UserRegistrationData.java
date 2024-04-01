package com.udea.serviagenda.dominio.user.dto;

import com.udea.serviagenda.dominio.user.model.Role;

public record UserRegistrationData(
        String name,
        String lastName,
        int userId,
        String phone,
        String email,
        String password,
        Role role
) {
}
