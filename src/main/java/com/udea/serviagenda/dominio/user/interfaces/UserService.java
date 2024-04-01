package com.udea.serviagenda.dominio.user.interfaces;

import com.udea.serviagenda.dominio.user.dto.UserData;
import com.udea.serviagenda.dominio.user.dto.UserRegistrationClientData;
import com.udea.serviagenda.dominio.user.dto.UserRegistrationData;

public interface UserService {
    public UserData registerUserClient(UserRegistrationClientData userRegistrationClientData);
    public UserData registerUserEmploye(UserRegistrationData userRegistrationData);
}
