package com.example.sigmback.repository;

import com.example.sigmback.model.Geologie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface IGeologieRepository extends JpaRepository<Geologie,Long> {

    /*@Query(value= "select g.depthTo,g.depthFrom from Geologie g where g.puissance= g.depthTo-g.depthFrom")
    Float calculerPuissance();*/

}
