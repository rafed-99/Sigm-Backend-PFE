package com.example.sigmback.service;

import com.example.sigmback.model.*;
import com.example.sigmback.repository.ICoucheRepository;
import com.example.sigmback.repository.IGeologieRepository;
import com.example.sigmback.repository.IPointRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GeologieService implements IGeologieService{

    @Autowired
    IGeologieRepository iGeologieRepository;

    @Autowired
    IPointRepository iPointRepository;

    @Autowired
    ICoucheRepository iCoucheRepository;


    @Override
    public Geologie addGeologie(Geologie geologie) {

        Float dTo = geologie.getDepthTo();
        Float dFrom = geologie.getDepthFrom();
        geologie.setPuissance(dTo-dFrom) ;
        System.out.println("puissance: "+geologie.getPuissance());
        return iGeologieRepository.save(geologie);
    }



    @Override
    public Geologie updateGeologie(Geologie geologie) {

        geologie.setDepthTo(geologie.getDepthTo());
        geologie.setDepthFrom(geologie.getDepthFrom());
        Float dTo = geologie.getDepthTo();
        Float dFrom = geologie.getDepthFrom();
        geologie.setPuissance(dTo-dFrom);
        return iGeologieRepository.save(geologie);
    }



    @Override
    public void deleteGeologie(Long id_geologie) {

        iGeologieRepository.deleteById(id_geologie);
    }

    @Override
    public List<Geologie> retrieveGeologies() {

        return (List<Geologie>)iGeologieRepository.findAll();
    }

    @Override
    public Geologie retrieveOneGeologie(Long id_geologie) {

        return iGeologieRepository.findById(id_geologie).get();
    }

    @Override
    public List<Geologie> retrieveGeologieByPoint(long id_point) {
        return iPointRepository.findById(id_point).get().getGeologies();
    }

    public Geologie addwithaffectation (Long id_point, Long id_couche, Geologie geologie){
        Point point = iPointRepository.findById(id_point).orElse(null);
        Couche couche = iCoucheRepository.findById(id_couche).orElse(null);
        geologie.setPoint(point);
        geologie.setCouche(couche);
        Float dTo = geologie.getDepthTo();
        Float dFrom = geologie.getDepthFrom();
        geologie.setPuissance(dTo-dFrom) ;
        System.out.println("puissance: "+geologie.getPuissance());
        return iGeologieRepository.save(geologie);
    }


}
