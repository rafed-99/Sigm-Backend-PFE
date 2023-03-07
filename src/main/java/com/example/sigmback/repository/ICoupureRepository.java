package com.example.sigmback.repository;

import com.example.sigmback.model.Coupure;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICoupureRepository extends JpaRepository<Coupure,Long> {
}
