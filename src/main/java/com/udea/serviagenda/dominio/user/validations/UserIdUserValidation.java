package com.udea.serviagenda.dominio.user.validations;


import com.udea.serviagenda.dominio.user.UserRepository;
import com.udea.serviagenda.dominio.user.dto.UserRegistrationClientData;
import com.udea.serviagenda.dominio.user.dto.UserRegistrationData;
import com.udea.serviagenda.dominio.user.dto.UserUpdateData;
import com.udea.serviagenda.infra.exceptions.CustomValidationException;
import org.springframework.stereotype.Component;




@Component
public class UserIdUserValidation implements com.udea.serviagenda.dominio.user.validations.UserValidator {

    private UserRepository userRepository;

    public UserIdUserValidation(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void validate(UserRegistrationClientData userRegistrationClientData) {
        validateIdUser(userRegistrationClientData.userId());
    }

    @Override
    public void validate(UserRegistrationData userRegistrationData) {
        validateIdUser(userRegistrationData.userId());
    }

    @Override
    public void validate(UserUpdateData userUpdateData) {
        validateIdUser(userUpdateData.userId());
    }

    private void validateIdUser(int userid) {
        if (this.userRepository.existsByUserId(userid)) {
            throw new CustomValidationException("idUser",
                    "El ID (" + userid + ") ya esta en uso.");
        }
    }
}

