package com.example.sigmback.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static com.example.sigmback.model.Permission.*;

@RequiredArgsConstructor
public enum Role {


  USER(Collections.emptySet()),
//    USER(
//            Set.of(
//                    USER_READ
//            )
//),


  ADMIN(

          Set.of(
                  ADMIN_READ,
                  ADMIN_UPDATE,
                  ADMIN_DELETE,
                  ADMIN_CREATE
          )
  ),

    GEOLOGIE_ADMIN(
          Set.of(
                  GEOLOGIE_ADMIN_READ,
                  GEOLOGIE_ADMIN_UPDATE,
                  GEOLOGIE_ADMIN_DELETE,
                  GEOLOGIE_ADMIN_CREATE
          )
  ),

  GEOLOGIE_USER(
          Set.of(
                  GEOLOGIE_USER_READ,
                  GEOLOGIE_USER_UPDATE,
                  GEOLOGIE_USER_CREATE
          )
  ),

  GEOLOGIE_CONSULT(
          Set.of(
                  GEOLOGIE_CONSULT_READ

          )
  ),

  CENTRE_ADMIN(
          Set.of(
                  CENTRE_ADMIN_READ,
                  CENTRE_ADMIN_UPDATE,
                  CENTRE_ADMIN_DELETE,
                  CENTRE_ADMIN_CREATE
          )
  ),

  CENTRE_CONFIRM(
          Set.of(
                  CENTRE_CONFIRM_READ,
                  CENTRE_CONFIRM_UPDATE,
                  CENTRE_CONFIRM_DELETE,
                  CENTRE_CONFIRM_CREATE
          )
  ),

  CENTRE_USER(
          Set.of(
                  CENTRE_USER_READ,
                  CENTRE_USER_UPDATE,
                  CENTRE_USER_DELETE,
                  CENTRE_USER_CREATE
          )
  )


  ;

  @Getter
  @JsonIgnore
  private final Set<Permission> permissions;


  @JsonIgnore
  public List<SimpleGrantedAuthority> getAuthorities() {
    var authorities = getPermissions()
            .stream()
            .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
            .collect(Collectors.toList());
    authorities.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
    return authorities;
  }
}
