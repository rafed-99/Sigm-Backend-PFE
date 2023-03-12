package com.example.sigmback.repository;

import com.example.sigmback.model.Analyses;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IAnalyseRepository extends JpaRepository<Analyses,Long> {
}
