package com.example.sigmback.service;

import com.example.sigmback.model.Echantillon;
import com.example.sigmback.model.Geologie;
import com.example.sigmback.repository.IBordereauRepository;
import com.example.sigmback.repository.IEchantillonRepository;
import com.example.sigmback.repository.IGeologieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

@Service
public class EchantillonService implements IEchantillonService{

    @Autowired
    IEchantillonRepository iEchantillonRepository;

    @Autowired
    IGeologieRepository iGeologieRepository;

    @Autowired
    IGeologieService iGeologieService;

    @Autowired
    IBordereauRepository iBordereauRepository;

    @Override
    public Echantillon addEchantillon(Echantillon echantillon) {

        return iEchantillonRepository.save(echantillon);
    }

    @Override
    public Echantillon updateEchantillon(Echantillon echantillon) {

        return iEchantillonRepository.save(echantillon);
    }

    @Override
    public void deleteEchantillon(Long id_echantillon) {

        iEchantillonRepository.deleteById(id_echantillon);
    }

    @Override
    public List<Echantillon> retrieveEchantillons() {

        return (List<Echantillon>)iEchantillonRepository.findAll();
    }

    @Override
    public Echantillon retrieveOneEchantillon(Long id_echantillon) {

        return iEchantillonRepository.findById(id_echantillon).get();
    }

    @Override
    public List<Echantillon> retrieveEchantillonsByGeologie(Long id_geologie) {
        return iGeologieRepository.findById(id_geologie).get().getEchantillons();
    }
    public List<Echantillon> retrieveEchantillonByPoint(Long id_point){
        List<Echantillon> listech = new ArrayList<>();
        List <Geologie> list = iGeologieService.retrieveGeologieByPoint(id_point);
        for(Geologie g : list){
            listech= Stream.concat(listech.stream(), retrieveEchantillonsByGeologie(g.getGeologieId()).stream()).toList();//retrieveEchantillonsByGeologie(g.getGeologieId());
        }
        return listech;
    }

    public List<Echantillon> retrieveEchantillonByBordereau(Long id_bordereau){

        return iBordereauRepository.findById(id_bordereau).get().getEchantillons();
    }
}
