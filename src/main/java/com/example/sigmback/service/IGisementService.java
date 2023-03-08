package com.example.sigmback.service;

import com.example.sigmback.model.Gisement;
import com.example.sigmback.model.Point;

import java.util.List;

public interface IGisementService {
    Gisement addGisement(Gisement gisement);
    void deleteGisement(Long id_gisement);
    Gisement updateGisement(Gisement gisement);
    List<Gisement> retrieveGisement();
    Gisement retrieveOneGisement(Long id_gisement);

    List<Gisement> findGisementsBySecteur(String secteur);

    Long countGisementBySecteur(String secteur);

   /* List<Gisement> orderGisementBySecteurAsc();

    List<Gisement> orderGisementBySecteurDesc();*/

    /*List<Gisement> searchByLibelle(String libelle);*/
}
