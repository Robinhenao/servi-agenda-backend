package com.udea.serviagenda.dominio.user;

import com.udea.serviagenda.dominio.user.dto.UserData;
import com.udea.serviagenda.dominio.user.dto.UserRegistrationData;
import com.udea.serviagenda.dominio.user.dto.UserUpdateData;
import com.udea.serviagenda.dominio.user.interfaces.EmployeService;
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
public class EmployeServiceImp implements EmployeService {

    private static final Logger logger =  LoggerFactory.getLogger(EmployeServiceImp.class);
    private UserRepository userRepository;
    private List<UserValidator> validators;
    private PasswordEncoder passwordEncoder;

    public EmployeServiceImp(UserRepository userRepository, List<UserValidator> validators, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.validators = validators;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserData registerUserEmploye(UserRegistrationData userRegistrationData) {
        logger.debug("Attempting to create user with ID: {}", userRegistrationData.name());
        List<CustomValidationException> exceptions = new ArrayList<>();
        validators.forEach(v -> {
            try {
                v.validate(userRegistrationData);
            } catch (CustomValidationException e) {
                exceptions.add(e);
            }
        });
        if (!exceptions.isEmpty()) {
            logger.error("Validation failed: {}", exceptions);
            throw new DataIntegrityValidationException(exceptions);
        }
        String encodedPassword = passwordEncoder.encode(userRegistrationData.password());
        User user = new User(
                userRegistrationData.name(),
                userRegistrationData.lastName(),
                userRegistrationData.userId(),
                userRegistrationData.phone(),
                userRegistrationData.email(),
                encodedPassword,
                userRegistrationData.role(),
                true,
                true,
                true,
                true);
        user = this.userRepository.save(user);
        logger.info("User with ID {} create successfully", user.getUserId());
        return new UserData(user);
    }

    @Override
    public UserData updateUserEmploye(UserUpdateData userUpdateData) {
        logger.debug("Attempting to update user with ID: {}", userUpdateData.idUser());
        List<CustomValidationException> exceptions = new ArrayList<>();
        validators.forEach(v -> {
            try {
               // v.validate(userUpdateData);
            } catch (CustomValidationException e) {
                logger.error("Validation failed for user update", e);
                exceptions.add(e);
            }
        });
        if (!exceptions.isEmpty()) {
            throw new DataIntegrityValidationException(exceptions);
        }
        User user = userRepository.findByIdUser(userUpdateData.idUser());
        BeanUtils.copyProperties(userUpdateData, user, "id", "enabled", "accountNonExpired", "credentialsNonExpired", "accountNonLocked");
        user = userRepository.save(user);
        logger.info("User with ID {} updated successfully", user.getUserId());
        return new UserData(user);
    }

    @Override
    public UserData getUserEmploye(int userId) {
        logger.debug("Fetching user with ID: {}", userId);
        return this.userRepository.findByUserIdAndRoleNot(userId, Role.CLIENT);
    }

    @Override
    public List<UserData> getUserEmployes() {
        logger.debug("Fetching all users");
        return this.userRepository.findAllByRoleNot(Role.CLIENT);
    }

    @Override
    public UserData deleteUserEmploye(int idUser) {
        logger.debug("Deleting user with ID: {}", idUser);
        User user = userRepository.findByIdUser(idUser);
        user.deleteUser();
        userRepository.save(user);
        logger.info("User with ID {} deleted successfully", idUser);
        return new UserData(user);
    }
}
