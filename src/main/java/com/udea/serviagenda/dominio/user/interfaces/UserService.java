package com.udea.serviagenda.dominio.user.interfaces;

import com.udea.serviagenda.dominio.user.dto.UserData;
import com.udea.serviagenda.dominio.user.dto.UserRegistrationClientData;
import com.udea.serviagenda.dominio.user.dto.UserRegistrationData;
import com.udea.serviagenda.dominio.user.dto.UserUpdateData;

import java.util.List;

public interface UserService {
    public UserData registerUserClient(UserRegistrationClientData userRegistrationClientData);

    public UserData updateUserClient(UserUpdateData userUpdateData);
    public UserData getUserClient(int id);
    public List<UserData> getUserClients();
    public UserData deleteUserClient(int id);

}
