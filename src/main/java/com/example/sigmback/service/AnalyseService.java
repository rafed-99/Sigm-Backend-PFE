package com.example.sigmback.service;


import com.example.sigmback.model.Analyse;
import com.example.sigmback.repository.IAnalyseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnalyseService implements IAnalyseService{

    @Autowired
    IAnalyseRepository iAnalyseRepository;

    @Override
    public Analyse addAnalyse(Analyse analyse) {
        return iAnalyseRepository.save(analyse);
    }

    @Override
    public Analyse updateAnalyse(Analyse analyse) {
        return iAnalyseRepository.save(analyse);
    }

    @Override
    public void deleteAnalyse(Long id_analyse) {
        iAnalyseRepository.deleteById(id_analyse);
    }

    @Override
    public List<Analyse> retrieveAnalyses() {
        return iAnalyseRepository.findAll();
    }

    @Override
    public Analyse retrieveOneAnalyse(Long id_analyse) {
        return iAnalyseRepository.findById(id_analyse).get();
    }
}
