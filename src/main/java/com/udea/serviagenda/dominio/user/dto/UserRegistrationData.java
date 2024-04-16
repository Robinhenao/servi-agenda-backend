package com.udea.serviagenda.dominio.user.dto;

import com.udea.serviagenda.dominio.user.model.Role;
import jakarta.validation.constraints.*;

public record UserRegistrationData(
        @NotBlank(message = "El nombre no puede estar en blanco")
        String name,
        @NotBlank(message = "El apellido no puede estar en blanco")
        String lastName,
        @Min(value = 1, message = "El ID de usuario debe ser un número positivo")
        int userId,
        @NotBlank(message = "El número de teléfono no puede estar en blanco")
        String phone,
        @Email(message = "Debe ser una dirección de correo electrónico válida")
        String email,
        @Size(min = 8, message = "La contraseña debe tener al menos 8 caracteres")
        String password,
        @NotNull(message = "El rol no puede estar vacío")
        Role role
){
}
