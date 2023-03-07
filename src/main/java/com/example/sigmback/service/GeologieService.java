package com.example.sigmback.service;

import com.example.sigmback.model.Geologie;
import com.example.sigmback.repository.IGeologieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GeologieService implements IGeologieService{

    @Autowired
    IGeologieRepository iGeologieRepository;



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

}
