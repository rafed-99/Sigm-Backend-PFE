package com.example.sigmback.service;

import com.example.sigmback.model.Coupure;

import java.util.List;

public interface ICoupureService {
    List<Coupure> retrieveCoupures();

    Coupure retrieveOneCoupure(Long id_coupure);
}
