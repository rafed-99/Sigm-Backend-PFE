package com.example.sigmback.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Permission {


    ADMIN_READ("admin:read"),
    ADMIN_UPDATE("admin:update"),
    ADMIN_CREATE("admin:create"),
    ADMIN_DELETE("admin:delete"),
    GEOLOGIE_ADMIN_READ("geologieadmin:read"),
    GEOLOGIE_ADMIN_UPDATE("geologieadmin:update"),
    GEOLOGIE_ADMIN_CREATE("geologieadmin:create"),
    GEOLOGIE_ADMIN_DELETE("geologieadmin:delete"),
    GEOLOGIE_USER_READ("geologieuser:read"),
    GEOLOGIE_USER_UPDATE("geologieuser:update"),
    GEOLOGIE_USER_CREATE("geologieuser:create"),

    GEOLOGIE_CONSULT_READ("geologieconsult:read"),
    CENTRE_ADMIN_READ("centreadmin:read"),
    CENTRE_ADMIN_UPDATE("centreadmin:update"),
    CENTRE_ADMIN_DELETE("centreadmin:delete"),
    CENTRE_ADMIN_CREATE("centreadmin:create"),
    CENTRE_CONFIRM_READ("centreconfirm:read"),
    CENTRE_CONFIRM_UPDATE("centreconfirm:update"),
    CENTRE_CONFIRM_DELETE("centreconfirm:delete"),
    CENTRE_CONFIRM_CREATE("centreconfirm:create"),
    CENTRE_USER_READ("centreuser:read"),
    CENTRE_USER_UPDATE("centreuser:update"),
    CENTRE_USER_DELETE("centreuser:delete"),
    CENTRE_USER_CREATE("centreuser:create"),

    USER_READ("user:read")

    ;

    @Getter
    @JsonIgnore
    private final String permission;
}
