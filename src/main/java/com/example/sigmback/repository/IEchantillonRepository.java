package com.example.sigmback.repository;

import com.example.sigmback.model.Echantillon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface IEchantillonRepository extends JpaRepository<Echantillon,Long> {

    @Query("select count(g) From Echantillon g where g.etatEchantillon='AVerifier'")
    Long countToVerify();

    @Query("select count(g) From Echantillon g where g.etatEchantillon='Envoye'")
    Long countSent();

    @Query("select count(g) From Echantillon g where g.etatEchantillon='Recu'")
    Long countReceived();

    @Query("select count(g) From Echantillon g where g.etatEchantillon='Analyse'")
    Long countAnalysed();
}
