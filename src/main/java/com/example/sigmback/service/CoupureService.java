package com.example.sigmback.service;

import com.example.sigmback.model.Coupure;
import com.example.sigmback.repository.ICoupureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CoupureService implements ICoupureService{

    @Autowired
    ICoupureRepository iCoupureRepository;

    @Override
    public Coupure addCoupure(Coupure coupure) {

        return iCoupureRepository.save(coupure);
    }

    @Override
    public Coupure updateCoupure(Coupure coupure) {

        return iCoupureRepository.save(coupure);
    }

    @Override
    public void deleteCoupure(Long id_coupure) {

        iCoupureRepository.deleteById(id_coupure);
    }

    @Override
    public List<Coupure> retrieveCoupures() {

        return (List<Coupure>)iCoupureRepository.findAll();
    }

    @Override
    public Coupure retrieveOneCoupure(Long id_coupure) {

        return iCoupureRepository.findById(id_coupure).get();
    }
}
