package com.udea.serviagenda.dominio.user;

import com.udea.serviagenda.dominio.user.dto.UserData;
import com.udea.serviagenda.dominio.user.dto.UserRegistrationClientData;
import com.udea.serviagenda.dominio.user.dto.UserUpdateData;
import com.udea.serviagenda.dominio.user.interfaces.UserService;
import com.udea.serviagenda.dominio.user.model.Role;
import com.udea.serviagenda.dominio.user.model.User;
import com.udea.serviagenda.dominio.user.validations.UserValidator;
import com.udea.serviagenda.infra.exceptions.CustomValidationException;
import com.udea.serviagenda.infra.exceptions.DataIntegrityValidationException;
import org.springframework.beans.BeanUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImp implements UserService {

    private UserRepository userRepository;
    private List<UserValidator> validators;
    private PasswordEncoder passwordEncoder;

    public UserServiceImp(UserRepository userRepository, List<UserValidator> validators, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.validators = validators;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserData registerUserClient(UserRegistrationClientData userRegistrationClientData) {
        List<CustomValidationException> exceptions = new ArrayList<>();
        validators.forEach(v -> {
            try {
                v.validate(userRegistrationClientData);
            } catch (CustomValidationException e) {
                exceptions.add(e);
            }
        });
        if (!exceptions.isEmpty()) {
            throw new DataIntegrityValidationException(exceptions);
        }
        String encodedPassword = passwordEncoder.encode(userRegistrationClientData.password());
        User user = new User(
                userRegistrationClientData.name(),
                userRegistrationClientData.lastName(),
                userRegistrationClientData.userId(),
                userRegistrationClientData.phone(),
                userRegistrationClientData.email(),
                encodedPassword,
                Role.CLIENT,
                true,
                true,
                true,
                true);
        user = this.userRepository.save(user);
        return new UserData(user);
    }

    @Override
    public UserData updateUserClient(UserUpdateData userUpdateData) {
        List<CustomValidationException> exceptions = new ArrayList<>();
        validators.forEach(v -> {
            try {
                //v.validate(userUpdateData);
            } catch (CustomValidationException e) {
                exceptions.add(e);
            }
        });
        if (!exceptions.isEmpty()) {
            throw new DataIntegrityValidationException(exceptions);
        }
        User user = userRepository.findByIdUser(userUpdateData.idUser());
        BeanUtils.copyProperties(userUpdateData, user, "id", "password", "enabled", "accountNonExpired", "credentialsNonExpired", "accountNonLocked");
        user = userRepository.save(user);
        return new UserData(user);
    }

    @Override
    public UserData getUserClient(int userId) {
        return this.userRepository.findByUserIdAndRole( userId,Role.CLIENT);
    }

    @Override
    public List<UserData> getUserClients() {
        return this.userRepository.findByRole(Role.CLIENT);
    }

    @Override
    public UserData deleteUserClient(int idUser) {
        User user = userRepository.findByIdUser(idUser);
        user.deleteUser();
        userRepository.save(user);
        return new UserData(user);
    }


}
