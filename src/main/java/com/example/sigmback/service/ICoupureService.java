package com.example.sigmback.service;

import com.example.sigmback.model.Coupure;

import java.util.List;

public interface ICoupureService {

    Coupure addCoupure(Coupure coupure);

    Coupure updateCoupure(Coupure coupure);

    void deleteCoupure(Long id_coupure);

    List<Coupure> retrieveCoupures();

    Coupure retrieveOneCoupure(Long id_coupure);
}
