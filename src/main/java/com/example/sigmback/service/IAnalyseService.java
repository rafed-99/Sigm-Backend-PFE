package com.example.sigmback.service;

import com.example.sigmback.model.Analyses;

import java.util.List;

public interface IAnalyseService {

    Analyses addAnalyse(Analyses analyse);

    Analyses updateAnalyse(Analyses analyse);

    void deleteAnalyse(Long id_analyse);

    List<Analyses> retrieveAnalyses();

    Analyses retrieveOneAnalyse(Long id_analyse);

    List<Analyses> retrieveAnalyseByEchantillon(Long id_echantillon);


}
