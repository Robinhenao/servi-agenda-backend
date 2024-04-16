package com.udea.serviagenda.dominio.user;

import com.udea.serviagenda.dominio.user.dto.UserData;
import com.udea.serviagenda.dominio.user.model.Role;
import com.udea.serviagenda.dominio.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    UserDetails findByEmail(String email);

    boolean existsByEmail(String email);

    User findByIdUser(int idUser);
    List<UserData> findByRole(Role role);

    List<UserData> findAllByRoleNot(Role role);

    UserData findByUserIdAndRoleNot(int userId, Role role);

    UserData findByUserIdAndRole(int userId, Role role);

    boolean existsByUserId(int userid);
}
