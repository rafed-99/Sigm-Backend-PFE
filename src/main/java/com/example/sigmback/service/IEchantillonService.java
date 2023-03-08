package com.example.sigmback.service;

import com.example.sigmback.model.Echantillon;

import java.util.List;

public interface IEchantillonService {

    Echantillon addEchantillon(Echantillon echantillon);

    Echantillon updateEchantillon(Echantillon echantillon);

    void deleteEchantillon(Long id_echantillon);

    List<Echantillon> retrieveEchantillons();

    Echantillon retrieveOneEchantillon(Long id_echantillon);


    List<Echantillon> retrieveEchantillonsByGeologie(Long id_geologie);
}
