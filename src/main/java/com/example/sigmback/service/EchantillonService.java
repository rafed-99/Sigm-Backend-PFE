package com.example.sigmback.service;

import com.example.sigmback.model.Echantillon;
import com.example.sigmback.repository.IEchantillonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EchantillonService implements IEchantillonService{

    @Autowired
    IEchantillonRepository iEchantillonRepository;

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
}
