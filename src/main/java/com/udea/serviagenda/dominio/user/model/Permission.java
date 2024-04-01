package com.udea.serviagenda.dominio.user.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Permission {
    ADMIN_READ("admin:read"),
    ADMIN_UPDATE("admin:update"),
    ADMIN_CREATE("admin:create"),
    ADMIN_DELETE("admin:delete"),
    EMPLOYE_READ("employe:read"),
    EMPLOYE_UPDATE("employe:update"),
    EMPLOYE_CREATE("employe:create"),
    EMPLOYE_DELETE("employe:delete"),
    CLIENT_READ("client:read"),
    CLIENT_UPDATE("client:update"),
    CLIENT_CREATE("client:create"),
    CLIENT_DELETE("client:delete");

    @Getter
    private final String permission;
}