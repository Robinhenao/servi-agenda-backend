package com.udea.serviagenda.dominio.user.interfaces;

import com.udea.serviagenda.dominio.user.dto.UserData;
import com.udea.serviagenda.dominio.user.dto.UserRegistrationData;
import com.udea.serviagenda.dominio.user.dto.UserUpdateData;

import java.util.List;

public interface EmployeService {
    public UserData registerUserEmploye(UserRegistrationData userRegistrationData);
    public UserData updateUserEmploye(UserUpdateData userUpdateData);
    public UserData getUserEmploye(int id);
    public List<UserData> getUserEmployes();
    public UserData deleteUserEmploye(int id);

}
