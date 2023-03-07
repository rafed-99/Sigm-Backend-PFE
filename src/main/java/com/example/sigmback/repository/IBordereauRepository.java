package com.example.sigmback.repository;

import com.example.sigmback.model.Bordereau;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IBordereauRepository extends JpaRepository<Bordereau,Long> {
}
