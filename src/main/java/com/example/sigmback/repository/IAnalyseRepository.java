package com.example.sigmback.repository;

import com.example.sigmback.model.Analyses;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface IAnalyseRepository extends JpaRepository<Analyses,Long> {

    @Query("select count(g) From Analyses g where g.etatAnalyse='Nouvelle'")
    Long countNew();

    @Query("select count(g) From Analyses g where g.etatAnalyse='Confirme'")
    Long countConfirmed();

    @Query("select count(g) From Analyses g where g.etatAnalyse='Valide'")
    Long countValid();
}
