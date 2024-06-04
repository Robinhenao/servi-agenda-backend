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

    @Query("SELECT u FROM User u JOIN u.roles r WHERE r.name = :roleName")
    List<UserData> findByRoleName(String roleName);


    @Query("SELECT u FROM User u WHERE u.id NOT IN (SELECT DISTINCT u2.id FROM User u2 JOIN u2.roles r WHERE r.name = :roleName)")
    List<UserData>  findByRoleNameNot(String roleName);


    @Query("SELECT u FROM User u WHERE u.idUser = :userId AND :roleName NOT MEMBER OF u.roles")
    UserData findByUserIdAndRoleNameNot(int userId, String roleName);

    @Query("SELECT u FROM User u JOIN u.roles r WHERE u.idUser = :userId AND r.name = :roleName")
    UserData findByUserIdAndRoleName(int userId, String roleName);


    boolean existsByUserId(int userid);
}
