package com.example.sigmback.repository;

import com.example.sigmback.model.Echantillon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IEchantillonRepository extends JpaRepository<Echantillon,Long> {
}
