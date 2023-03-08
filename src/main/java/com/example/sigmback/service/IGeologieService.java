package com.example.sigmback.service;

import com.example.sigmback.model.Echantillon;
import com.example.sigmback.model.Geologie;

import java.util.List;

public interface IGeologieService {

    Geologie addGeologie(Geologie geologie);

    Geologie updateGeologie(Geologie geologie);

    void deleteGeologie(Long id_geologie);

    List<Geologie> retrieveGeologies();

    Geologie retrieveOneGeologie(Long id_geologie);

    List<Geologie> retrieveGeologieByPoint(long id_point);

}
