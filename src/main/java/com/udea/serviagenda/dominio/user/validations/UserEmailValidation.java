package com.udea.serviagenda.dominio.user.validations;

import com.udea.serviagenda.dominio.user.UserRepository;
import com.udea.serviagenda.dominio.user.dto.UserRegistrationClientData;
import com.udea.serviagenda.dominio.user.dto.UserRegistrationData;
import com.udea.serviagenda.dominio.user.dto.UserUpdateData;
import com.udea.serviagenda.infra.exceptions.CustomValidationException;
import org.springframework.stereotype.Component;




@Component
public class UserEmailValidation implements com.udea.serviagenda.dominio.user.validations.UserValidator {
    
    private UserRepository userRepository;

    public UserEmailValidation(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override
    public void validate(UserRegistrationClientData userRegistrationClientData) {
        validateEmail(userRegistrationClientData.email());
    }

    @Override
    public void validate(UserRegistrationData userRegistrationData) {
        validateEmail(userRegistrationData.email());
    }

    @Override
    public void validate(UserUpdateData userUpdateData) {
        validateEmail(userUpdateData.email());
    }

    private void validateEmail(String email) {
        if (this.userRepository.existsByEmail(email)){
            throw new CustomValidationException("email",
                    "El correo (" + email + ") ya esta en uso.");
        }
    }
}


