package com.example.sigmback.repository;

import java.util.Optional;

import com.example.sigmback.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User, Long> {

  Optional<User> findByEmail(String email);

  @Query("select count(g) From User g where g.role='ADMIN'")
  Long countAdminUser();

  @Query("select count(g) From User g where g.role='GEOLOGIE_ADMIN'")
  Long countGeologyAdminUser();

  @Query("select count(g) From User g where g.role='GEOLOGIE_USER'")
  Long countGeologyUser();

  @Query("select count(g) From User g where g.role='GEOLOGIE_CONSULT'")
  Long countGeologyConsultantUser();

  @Query("select count(g) From User g where g.role='CENTRE_ADMIN'")
  Long countCenterAdminUser();

  @Query("select count(g) From User g where g.role='CENTRE_CONFIRM'")
  Long countCenterUser();

  @Query("select count(g) From User g where g.role='CENTRE_USER'")
  Long countCenterConfirmUser();

}
