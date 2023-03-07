package com.example.sigmback.repository;

import com.example.sigmback.model.Couche;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICoucheRepository extends JpaRepository<Couche,Long> {
}
