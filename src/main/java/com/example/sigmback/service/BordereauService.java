package com.example.sigmback.service;

import com.example.sigmback.model.Bordereau;
import com.example.sigmback.repository.IBordereauRepository;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class BordereauService implements IBordereauService{

    @Autowired
    IBordereauRepository iBordereauRepository;


    @Override
    public Bordereau addBordereau(Bordereau bordereau) throws JRException, FileNotFoundException {

        iBordereauRepository.save(bordereau);
        Date dE = bordereau.getDateEnvoi();
        DateFormat dateFormat = new SimpleDateFormat("yyyy");
        String strDate = dateFormat.format(dE);
        System.out.println(strDate);
        String idBordereau = (bordereau.getBordereauId()).toString();
        System.out.println(idBordereau);
        bordereau.setBordereauCode(strDate+"-"+idBordereau);
        System.out.println(bordereau.getBordereauCode());
        iBordereauRepository.save(bordereau);
        //this.exportPdf(bordereau);

        /*String path = "C:\\ReportSigm";
        List<Bordereau> bordereaux = new ArrayList<>();
        bordereaux.add(bordereau);
        //load file and compile it
        File file = ResourceUtils.getFile("classpath:bordereau1.jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(bordereaux);
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("createdBy", "Java Techie");
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);


        JasperExportManager.exportReportToPdfFile(jasperPrint, path + "\\bordereau1.pdf");

        return file;*/

        //load file and compile it
        //File file = ResourceUtils.getFile("classpath:bordereaux.jrxml");
        /*JasperReport jasperReport = JasperCompileManager.compileReport(getClass().getResourceAsStream("/reports/bordereau1.jrxml"));
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(bordereaux);
        Map<String, Object> parameters = new HashMap<>();
        //parameters.put("", "");
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);
        JasperExportManager.exportReportToPdfFile(jasperPrint, path + "\\bordereau.pdf");
        File bordereau1 = new File(path +"\\bordereau.pdf" );
        return bordereau1;*/

    return bordereau;
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

    public void updateBordereau1(List<Bordereau> bordereau) {
        for(Integer i = 0; i<bordereau.size();i++){
            System.out.println(bordereau.get(i));
            iBordereauRepository.save(bordereau.get(i));
        }
    }
}
