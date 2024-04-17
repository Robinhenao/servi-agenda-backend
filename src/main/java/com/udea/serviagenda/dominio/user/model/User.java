package com.udea.serviagenda.dominio.user.model;


import com.udea.serviagenda.dominio.user.dto.UserRegistrationClientData;
import com.udea.serviagenda.dominio.user.dto.UserRegistrationData;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Collection;

@Entity
@Table(name = "users")
@Getter
@Setter
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

    @Column(name = "is_account_non_expired")
    Boolean isAccountNonExpired;

    @Column(name = "is_account_non_locked")
    Boolean isAccountNonLocked;

    @Column(name = "is_credentials_non_expired")
    Boolean isCredentialsNonExpired;

    @Column(name = "is_enabled")
    Boolean isEnabled;


    public User(String name, String lastName, int userId, String phone, String email, String password, Role role, Boolean isAccountNonExpired, Boolean isAccountNonLocked, Boolean isCredentialsNonExpired, Boolean isEnabled) {
        this.name = name;
        this.lastName = lastName;
        this.userId = userId;
        this.phone = phone;
        this.email = email;
        this.password = password;
        this.role = role;
        this.isAccountNonExpired = isAccountNonExpired;
        this.isAccountNonLocked = isAccountNonLocked;
        this.isCredentialsNonExpired = isCredentialsNonExpired;
        this.isEnabled = isEnabled;
    }

    public void deleteUser(){
        this.isEnabled=false;
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
        return this.isAccountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return this.isAccountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return this.isCredentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return this.isEnabled;
    }

}
