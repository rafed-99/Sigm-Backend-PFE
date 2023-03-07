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
}
