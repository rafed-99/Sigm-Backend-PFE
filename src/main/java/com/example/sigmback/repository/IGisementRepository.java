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

    @Query("select count (g) from Gisement g")
    Long countGisementBySecteur (@Param("secteur") String secteur);



  /*@Query("select g from Gisement g order by g.secteur asc ")
    List<Gisement> orderGisementBySecteurAsc ();

    @Query("select g from Gisement g order by g.secteur desc ")
    List<Gisement> orderGisementBySecteurDesc ();*/

    @Query("select g from Gisement g where g.gisementLibelle like %?1%")
    List<Gisement> searchByLibelle (@Param("libelle") String libelle);

//    @Query("select count(g)  from Gisement g  group by g.secteur order by g.secteur  desc")
//    List<Object[]> countbysecteur ();



    @Query("select g.secteur, count(g) FROM Gisement g group by g.secteur")
    List<Object[]> countbysecteur ();

    @Query("select count(g) From Gisement g")
    Long countGisement();

    @Query("select count(g) From Gisement g where g.secteur='Redeyef' ")
    Long countGisementBySecteurRedeyef();

    @Query("select count(g) From Gisement g where g.secteur='Moulares' ")
    Long countGisementBySecteurMoulares();

    @Query("select count(g) From Gisement g where g.secteur='Metlaoui' ")
    Long countGisementBySecteurMetlaoui();

    @Query("select count(g) From Gisement g where g.secteur='Mdhilla' ")
    Long countGisementBySecteurMdhilla();
}
