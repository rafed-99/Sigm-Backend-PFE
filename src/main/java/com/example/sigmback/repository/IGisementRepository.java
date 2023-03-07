package com.example.sigmback.repository;

import com.example.sigmback.model.Gisement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IGisementRepository extends JpaRepository<Gisement,Long> {

    @Query("select g from Gisement g where g.secteur=:secteur")
    List<Gisement> findGisementsBySecteur (@Param("secteur") String secteur);


}
