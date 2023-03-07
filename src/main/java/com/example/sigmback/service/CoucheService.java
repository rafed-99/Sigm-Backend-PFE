package com.example.sigmback.service;

import com.example.sigmback.model.Couche;
import com.example.sigmback.repository.ICoucheRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CoucheService implements ICoucheService{

    @Autowired
    ICoucheRepository iCoucheRepository;

    @Override
    public Couche addCouche(Couche couche) {
        return iCoucheRepository.save(couche);
    }

    @Override
    public Couche updateCouche(Couche couche) {
        return iCoucheRepository.save(couche);
    }

    @Override
    public void deleteCouche(Long id_couche) {
        iCoucheRepository.deleteById(id_couche);
    }

    @Override
    public List<Couche> retrieveCouches() {
        return (List<Couche>) iCoucheRepository.findAll();
    }

    @Override
    public Couche retrieveOneCouche(Long id_couche) {
        return iCoucheRepository.findById(id_couche).get();
    }
}
