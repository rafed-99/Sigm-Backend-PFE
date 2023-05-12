package com.example.sigmback.service;


import com.example.sigmback.model.Analyses;
import com.example.sigmback.repository.IAnalyseRepository;
import com.example.sigmback.repository.IEchantillonRepository;
import com.example.sigmback.repository.IElementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnalyseService implements IAnalyseService{

    @Autowired
    IAnalyseRepository iAnalyseRepository;

    @Autowired
    IEchantillonRepository iEchantillonRepository;

    @Autowired
    IElementRepository iElementRepository;

    @Override
    public Analyses addAnalyse(Analyses analyse) {
        return iAnalyseRepository.save(analyse);
    }

    @Override
    public Analyses updateAnalyse(Analyses analyse) {
        return iAnalyseRepository.save(analyse);
    }

    @Override
    public void deleteAnalyse(Long id_analyse) {
        iAnalyseRepository.deleteById(id_analyse);
    }

    @Override
    public List<Analyses> retrieveAnalyses() {
        return iAnalyseRepository.findAll();
    }

    @Override
    public Analyses retrieveOneAnalyse(Long id_analyse) {
        return iAnalyseRepository.findById(id_analyse).get();
    }

    @Override
    public List<Analyses> retrieveAnalyseByEchantillon(Long id_echantillon){

        return iEchantillonRepository.findById(id_echantillon).get().getAnalyses();
    }

    public List<Analyses> retrieveAnalyseByElements(Long id_element){

        return iElementRepository.findById(id_element).get().getAnalyses();
    }


}
