package com.udea.serviagenda.dominio.user.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public enum Role {
    USER(Collections.emptySet()),
    ADMIN(
            Set.of(
                    Permission.ADMIN_READ,
                    Permission.ADMIN_UPDATE,
                    Permission.ADMIN_CREATE,
                    Permission.ADMIN_DELETE,
                    Permission.EMPLOYE_READ,
                    Permission.EMPLOYE_UPDATE,
                    Permission.EMPLOYE_CREATE,
                    Permission.EMPLOYE_DELETE,
                    Permission.CLIENT_READ,
                    Permission.CLIENT_UPDATE,
                    Permission.CLIENT_CREATE,
                    Permission.CLIENT_DELETE
            )
    ),
    EMPLOYE(
            Set.of(
                    Permission.EMPLOYE_READ,
                    Permission.EMPLOYE_UPDATE,
                    Permission.EMPLOYE_CREATE,
                    Permission.EMPLOYE_DELETE
            )

    ),
    CLIENT(
            Set.of(
                    Permission.CLIENT_READ,
                    Permission.CLIENT_UPDATE,
                    Permission.CLIENT_CREATE,
                    Permission.CLIENT_DELETE
            )

    );

    @Getter
    private final Set<Permission> permissions;

    public List<SimpleGrantedAuthority> getAuthorities() {
        var authorities = getPermissions()
                .stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toList());
        authorities.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
        return authorities;
    }
}