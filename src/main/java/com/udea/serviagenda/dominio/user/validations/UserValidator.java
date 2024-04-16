package com.udea.serviagenda.dominio.user.validations;


import com.udea.serviagenda.dominio.user.dto.UserRegistrationClientData;
import com.udea.serviagenda.dominio.user.dto.UserRegistrationData;
import com.udea.serviagenda.dominio.user.dto.UserUpdateData;

public interface UserValidator {

    public void validate(UserRegistrationClientData userRegistrationClientData);

    public void validate(UserRegistrationData userRegistrationData);

    public void validate(UserUpdateData userUpdateData);

}
