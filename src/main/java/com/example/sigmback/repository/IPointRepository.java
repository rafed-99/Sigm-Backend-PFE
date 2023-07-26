package com.example.sigmback.repository;

import com.example.sigmback.model.Point;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IPointRepository extends JpaRepository<Point,Long> {

    @Query("select g.gisement.gisementLibelle, g.gisement.secteur, count(g) FROM Point g group by g.gisement.gisementLibelle, g.gisement.secteur")
    List<Object[]> countbygisement ();
}
