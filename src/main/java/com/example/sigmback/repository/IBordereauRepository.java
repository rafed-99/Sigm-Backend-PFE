package com.example.sigmback.repository;

import com.example.sigmback.model.Bordereau;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface IBordereauRepository extends JpaRepository<Bordereau,Long> {

    @Query("select count(g) From Bordereau g where g.etatsBordereaux='AVerifier'")
    Long countToVerify();

    @Query("select count(g) From Bordereau g where g.etatsBordereaux='En_Attente'")
    Long countOnHold();

    @Query("select count(g) From Bordereau g where g.etatsBordereaux='En_Cours'")
    Long countInProgress();

    @Query("select count(g) From Bordereau g where g.etatsBordereaux='Analyse'")
    Long countAnalysed();
}
