package com.udea.serviagenda.dominio.user;

import com.udea.serviagenda.dominio.user.dto.UserData;
import com.udea.serviagenda.dominio.user.dto.UserRegistrationClientData;
import com.udea.serviagenda.dominio.user.dto.UserRegistrationData;
import com.udea.serviagenda.dominio.user.interfaces.UserService;
import com.udea.serviagenda.dominio.user.model.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImp implements UserService {

    private UserRepository userRepository;

    private PasswordEncoder passwordEncoder;

    public UserServiceImp(UserRepository userRepository, PasswordEncoder passwordEncoder){
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserData registerUserClient(UserRegistrationClientData userRegistrationClientData) {
        User user = User.createUserClient(userRegistrationClientData, passwordEncoder);
        user = this.userRepository.save(user);
        return new UserData(user);
    }
    @Override
    public UserData registerUserEmploye(UserRegistrationData userRegistrationData) {
        User user = User.createUserEmploye(userRegistrationData, passwordEncoder);
        user = this.userRepository.save(user);
        return new UserData(user);
    }

}
