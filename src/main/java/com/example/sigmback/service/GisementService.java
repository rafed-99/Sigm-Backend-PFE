package com.example.sigmback.service;

import com.example.sigmback.model.Gisement;
import com.example.sigmback.model.Point;
import com.example.sigmback.repository.IGisementRepository;
import com.example.sigmback.repository.IPointRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GisementService implements IGisementService{

    @Autowired
    IGisementRepository iGisementRepository;

    @Autowired
    IPointRepository iPointRepository;
    @Override
    public Gisement addGisement(Gisement g) {

        return iGisementRepository.save(g);
    }

    @Override
    public void deleteGisement(Long id) {

        iGisementRepository.deleteById(id);
    }

    @Override
    public Gisement updateGisement(Gisement g) {

        return iGisementRepository.save(g);
    }

    @Override
    public List<Gisement> retrieveGisement() {

        return (List<Gisement>) iGisementRepository.findAll();
    }

    @Override
    public Gisement retrieveOneGisement(Long id) {

        return iGisementRepository.findById(id).get();
    }

    @Override
    public List<Point> retrievePointsByGisement(Long id_gisement) {

        return iGisementRepository.findById(id_gisement).get().getPoints();
    }

    @Override
    public List<Gisement> findGisementsBySecteur(String secteur) {

        return iGisementRepository.findGisementsBySecteur(secteur);
    }

    @Override
    public Long countGisementBySecteur(String secteur) {

        return iGisementRepository.countGisementBySecteur(secteur);
    }

    @Override
    public List<Gisement> orderGisementBySecteurAsc() {

        return iGisementRepository.orderGisementBySecteurAsc();
    }

    @Override
    public List<Gisement> orderGisementBySecteurDesc() {

        return iGisementRepository.orderGisementBySecteurDesc();
    }

    /*@Override
    public List<Gisement> searchByLibelle(String libelle) {
        if(libelle != null) {
            return iGisementRepository.searchByLibelle(libelle);
        }
        return iGisementRepository.findAll();
    }*/
}
