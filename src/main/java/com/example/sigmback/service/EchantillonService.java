package com.example.sigmback.service;

import com.example.sigmback.model.Bordereau;
import com.example.sigmback.model.Echantillon;
import com.example.sigmback.model.Geologie;
import com.example.sigmback.repository.IBordereauRepository;
import com.example.sigmback.repository.IEchantillonRepository;
import com.example.sigmback.repository.IGeologieRepository;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

@Service
public class EchantillonService implements IEchantillonService{

    @Autowired
    IEchantillonRepository iEchantillonRepository;

    @Autowired
    IGeologieRepository iGeologieRepository;

    @Autowired
    IGeologieService iGeologieService;

    @Autowired
    IBordereauRepository iBordereauRepository;

    @Override
    public Echantillon addEchantillon(Echantillon echantillon) {

        return iEchantillonRepository.save(echantillon);
    }

    @Override
    public Echantillon updateEchantillon(Echantillon echantillon) {

        return iEchantillonRepository.save(echantillon);
    }

    @Override
    public void deleteEchantillon(Long id_echantillon) {

        iEchantillonRepository.deleteById(id_echantillon);
    }

    @Override
    public List<Echantillon> retrieveEchantillons() {

        return (List<Echantillon>)iEchantillonRepository.findAll();
    }

    @Override
    public Echantillon retrieveOneEchantillon(Long id_echantillon) {

        return iEchantillonRepository.findById(id_echantillon).get();
    }

    @Override
    public List<Echantillon> retrieveEchantillonsByGeologie(Long id_geologie) {
        return iGeologieRepository.findById(id_geologie).get().getEchantillons();
    }
    public List<Echantillon> retrieveEchantillonByPoint(Long id_point){
        List<Echantillon> listech = new ArrayList<>();
        List <Geologie> list = iGeologieService.retrieveGeologieByPoint(id_point);
        for(Geologie g : list){
            listech= Stream.concat(listech.stream(), retrieveEchantillonsByGeologie(g.getGeologieId()).stream()).toList();//retrieveEchantillonsByGeologie(g.getGeologieId());
        }
        return listech;
    }

    public List<Echantillon> retrieveEchantillonByBordereau(Long id_bordereau){

        return iBordereauRepository.findById(id_bordereau).get().getEchantillons();
    }

    public Echantillon addWithIdGeologie(Long id_geologie, Echantillon echantillon){
        Geologie geologie = iGeologieRepository.findById(id_geologie).orElse(null);
        echantillon.setGeologie(geologie);
        return iEchantillonRepository.save(echantillon);
    }

    public File exportReport(List<Echantillon> echantillons , Long idBordereau) throws FileNotFoundException, JRException {

        Bordereau bordereau = iBordereauRepository.findById(idBordereau).orElse(null);
        System.out.println("bordereau : "+bordereau.getBordereauId());

        List<Echantillon> echantillons1 = new ArrayList<>();


        Integer i;
        for(i=0 ; i<echantillons.size();i++){
            System.out.println(i);
            echantillons.get(i).setBordereau(bordereau);
            iEchantillonRepository.save(echantillons.get(i));
            echantillons1.add(echantillons.get(i));
            System.out.println("echantillons.get(i) : "+echantillons.get(i).getBordereau().getBordereauId());

        }

        String path = "C:\\ReportSigm";

        //load file and compile it
        //File file = ResourceUtils.getFile("classpath:bordereaux.jrxml");
        /*JasperReport jasperReport = JasperCompileManager.compileReport(getClass().getResourceAsStream("/reports/bordereau1.jrxml"));
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(echantillons);
        Map<String, Object> parameters = new HashMap<>();
        //parameters.put("", "");
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);
        JasperExportManager.exportReportToPdfFile(jasperPrint, path + "\\bordereau.pdf");
        File bordereau1 = new File(path +"\\bordereau.pdf" );
        return bordereau1;*/
        //load file and compile it


        File file = ResourceUtils.getFile("classpath:bordereau1.jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(echantillons1);
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("createdBy", "Java Techie");
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);


        JasperExportManager.exportReportToPdfFile(jasperPrint, path + "\\bordereau1.pdf");

        return file;
    }
}
