package com.example.sigmback.service;

import com.example.sigmback.model.Bordereau;
import net.sf.jasperreports.engine.JRException;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;

public interface IBordereauService {

     Bordereau addBordereau(Bordereau bordereau) throws JRException, FileNotFoundException;

     Bordereau updateBordereau(Bordereau bordereau);

     void deleteBordereau(Long id_bordereau);

     List<Bordereau> retrieveBordereaux();

     Bordereau retrieveOneBordereau(Long id_bordereau);


}
