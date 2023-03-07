package com.example.sigmback.service;

import com.example.sigmback.model.Bordereau;
import com.example.sigmback.repository.IBordereauRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class BordereauService implements IBordereauService{

    @Autowired
    IBordereauRepository iBordereauRepository;

    @Override
    public Bordereau addBordereau(Bordereau bordereau) {

        iBordereauRepository.save(bordereau);
        Date dE = bordereau.getDateEnvoi();
        DateFormat dateFormat = new SimpleDateFormat("yyyy");
        String strDate = dateFormat.format(dE);
        System.out.println(strDate);
        String idBordereau = (bordereau.getBordereauId()).toString();
        System.out.println(idBordereau);
        bordereau.setBordereauCode(strDate+"-"+idBordereau);
        System.out.println(bordereau.getBordereauCode());
        return iBordereauRepository.save(bordereau);
    }

    @Override
    public Bordereau updateBordereau(Bordereau bordereau) {
        return iBordereauRepository.save(bordereau);
    }

    @Override
    public void deleteBordereau(Long id_bordereau) {
        iBordereauRepository.deleteById(id_bordereau);
    }

    @Override
    public List<Bordereau> retrieveBordereaux() {
        return (List<Bordereau>)iBordereauRepository.findAll();
    }

    @Override
    public Bordereau retrieveOneBordereau(Long id_bordereau) {
        return iBordereauRepository.findById(id_bordereau).get();
    }
}
