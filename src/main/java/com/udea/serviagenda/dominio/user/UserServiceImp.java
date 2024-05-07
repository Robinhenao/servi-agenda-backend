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


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class UserServiceImp implements UserService {
    private static final Logger logger =  LoggerFactory.getLogger(UserServiceImp.class);
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
        logger.debug("Attempting to create user with ID: {}", userRegistrationClientData.name());
        List<CustomValidationException> exceptions = new ArrayList<>();
        validators.forEach(v -> {
            try {
                v.validate(userRegistrationClientData);
            } catch (CustomValidationException e) {
                exceptions.add(e);
            }
        });
        if (!exceptions.isEmpty()) {
            logger.error("Validation failed: {}", exceptions);
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
        logger.info("User with ID {} create successfully", user.getUserId());
        return new UserData(user);
    }

    @Override
    public UserData updateUserClient(UserUpdateData userUpdateData) {
        logger.debug("Attempting to update user with ID: {}", userUpdateData.idUser());
        List<CustomValidationException> exceptions = new ArrayList<>();
        validators.forEach(v -> {
            try {
                //v.validate(userUpdateData);
            } catch (CustomValidationException e) {
                logger.error("Validation failed for user update", e);
                exceptions.add(e);
            }
        });
        if (!exceptions.isEmpty()) {

            throw new DataIntegrityValidationException(exceptions);
        }
        User user = userRepository.findByIdUser(userUpdateData.idUser());
        BeanUtils.copyProperties(userUpdateData, user, "id", "password", "enabled", "accountNonExpired", "credentialsNonExpired", "accountNonLocked");
        user = userRepository.save(user);
        logger.info("User with ID {} updated successfully", user.getUserId());
        return new UserData(user);
    }

    @Override
    public UserData getUserClient(int userId) {
        logger.debug("Fetching user with ID: {}", userId);
        return this.userRepository.findByUserIdAndRole( userId,Role.CLIENT);

    }

    @Override
    public List<UserData> getUserClients() {
        logger.debug("Fetching all clients");
        return this.userRepository.findByRole(Role.CLIENT);
    }

    @Override
    public UserData deleteUserClient(int idUser) {
        logger.debug("Deleting user with ID: {}", idUser);
        User user = userRepository.findByIdUser(idUser);
        user.deleteUser();
        userRepository.save(user);
        logger.info("User with ID {} deleted successfully", idUser);
        return new UserData(user);
    }


}
