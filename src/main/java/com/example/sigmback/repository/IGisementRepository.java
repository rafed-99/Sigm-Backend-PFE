package com.example.sigmback.repository;

import com.example.sigmback.model.Gisement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IGisementRepository extends JpaRepository<Gisement,Long> {
}
