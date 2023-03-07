package com.example.sigmback.service;

import com.example.sigmback.model.Analyse;
import com.example.sigmback.model.Archive;

import java.util.List;

public interface IAnalyseService {

    Analyse addAnalyse(Analyse analyse);

    Analyse updateAnalyse(Analyse analyse);

    void deleteAnalyse(Long id_analyse);

    List<Analyse> retrieveAnalyses();

    Analyse retrieveOneAnalyse(Long id_analyse);
}
