package com.example.sigmback.service;

import com.example.sigmback.model.Couche;

import java.util.List;

public interface ICoucheService {

    Couche addCouche(Couche couche);

    Couche updateCouche(Couche couche);

    void deleteCouche(Long id_couche);

    List<Couche> retrieveCouches();

    Couche retrieveOneCouche(Long id_couche);

}
