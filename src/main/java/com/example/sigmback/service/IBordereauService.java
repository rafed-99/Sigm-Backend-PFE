package com.example.sigmback.service;

import com.example.sigmback.model.Bordereau;

import java.util.List;

public interface IBordereauService {

     Bordereau addBordereau(Bordereau bordereau);

     Bordereau updateBordereau(Bordereau bordereau);

     void deleteBordereau(Long id_bordereau);

     List<Bordereau> retrieveBordereaux();

     Bordereau retrieveOneBordereau(Long id_bordereau);
}
