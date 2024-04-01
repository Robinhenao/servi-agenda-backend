package com.udea.serviagenda.dominio.user.model;


import com.udea.serviagenda.dominio.user.dto.UserRegistrationClientData;
import com.udea.serviagenda.dominio.user.dto.UserRegistrationData;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Collection;

@Entity
@Table(name = "users")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User implements UserDetails {

    @Id
    @Column(name = "id_user")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idUser;
    @Column(nullable = false)
    private String name;

    @Column(nullable = false, name = "last_name")
    private String lastName;

    @Column(nullable = false, name = "user_id")
    private int userId;

    @Column(nullable = false)
    private String phone;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    public User(String name, String lastName, int userId, String phone, String email, String password, Role role) {
        this.name = name;
        this.lastName = lastName;
        this.userId = userId;
        this.phone = phone;
        this.email = email;
        this.password = password;
        this.role = role;
    }
    public static User createUserClient(UserRegistrationClientData userRegistrationClientData, PasswordEncoder passwordEncoder) {
        String encodedPassword = passwordEncoder.encode(userRegistrationClientData.password());
        return new User(
                userRegistrationClientData.name(),
                userRegistrationClientData.lastName(),
                userRegistrationClientData.userId(),
                userRegistrationClientData.phone(),
                userRegistrationClientData.email(),
                encodedPassword,
                Role.CLIENT);
    }

    public static User createUserEmploye(UserRegistrationData userRegistrationData, PasswordEncoder passwordEncoder) {
        String encodedPassword = passwordEncoder.encode(userRegistrationData.password());
        return new User(
                userRegistrationData.name(),
                userRegistrationData.lastName(),
                userRegistrationData.userId(),
                userRegistrationData.phone(),
                userRegistrationData.email(),
                encodedPassword,
                userRegistrationData.role());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return role.getAuthorities();
    }
    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public String getPassword() {
        return this.password;
    }
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
