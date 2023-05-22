package com.example.sigmback.service;

import com.example.sigmback.model.Bordereau;
import com.example.sigmback.model.Echantillon;
import com.example.sigmback.model.Element;
import com.example.sigmback.model.Geologie;
import com.example.sigmback.repository.IBordereauRepository;
import com.example.sigmback.repository.IEchantillonRepository;
import com.example.sigmback.repository.IGeologieRepository;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
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



        for(Integer i=0 ; i<echantillons.size();i++){
            System.out.println(i);
            echantillons.get(i).setBordereau(bordereau);
            iEchantillonRepository.save(echantillons.get(i));
            echantillons1.add(echantillons.get(i));
            System.out.println("echantillons.get(i) : "+echantillons.get(i).getBordereau().getBordereauId());

        }

        //String path = "D:\\ReportSigm\\";

        String path = "D:\\ReportSigm";
        //load file and compile it
        //File file = ResourceUtils.getFile("classpath:bordereaux.jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(getClass().getResourceAsStream("/report/bordereau1.jrxml"));
        //JasperReport jasperReport = JasperCompileManager.compileReport(getClass().getResourceAsStream("bordereau1.jrxml"));

        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(echantillons1);
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("Dataset1", dataSource);
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);
        JasperExportManager.exportReportToPdfFile(jasperPrint, path + "\\bordereau.pdf");
        return new File(path + "\\bordereau.pdf");


        //load file and compile it

        /*File file = ResourceUtils.getFile("classpath:bordereau1.jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(echantillons);
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("Dataset1", dataSource);
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);
        JasperExportManager.exportReportToPdfFile(jasperPrint, path + "\\bordereau1.pdf");

        return file;*/
    }

    public void generateExcelElement(HttpServletResponse response , Long id_geologie) throws Exception {

        List<Echantillon> echantillons = iGeologieRepository.findById(id_geologie).get().getEchantillons();
        //List<Geologie> geologies = iGeologieRepository.findAll();

        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("Layers");
        HSSFRow row = sheet.createRow(0);

        row.createCell(0).setCellValue("Id Sample");
        row.createCell(1).setCellValue("Layer Code");
        row.createCell(2).setCellValue("Depth From");
        row.createCell(3).setCellValue("Depth To");
        row.createCell(4).setCellValue("Real Volume");
        row.createCell(5).setCellValue("Observation");
        //row.createCell(6).setCellValue("Sample Status");

        /*Integer j=0;
        System.out.println(echantillons.size());
        for(Integer i=1;i<echantillons.size();i++){
            echantillons.get(i).getAnalyses().get(i).getElement().getElementCode();
            System.out.println(echantillons.get(i).getAnalyses().get(i).getElement().getElementCode());
            j++;
        }
        System.out.println(j);
        for(Integer k=5;k<j;k++) {
            for (Integer i = 1; i < echantillons.size(); i++) {
                row.createCell(k).setCellValue(echantillons.get(i).getAnalyses().get(i).getElement().getElementCode().toString());
            }
        }*/


        int dataRowIndex = 1;

        for (Echantillon echantillon : echantillons) {
            HSSFRow dataRow = sheet.createRow(dataRowIndex);
            //dataRow.createCell(0).setCellValue(geologie.getPoint().getHoleId());
            dataRow.createCell(0).setCellValue(echantillon.getEchantillonId());
            dataRow.createCell(1).setCellValue(echantillon.getGeologie().getCouche().getCoucheCode());
            dataRow.createCell(2).setCellValue(echantillon.getDepthFrom());
            dataRow.createCell(3).setCellValue(echantillon.getDepthTo());
            dataRow.createCell(4).setCellValue(echantillon.getPuissanceReelle());
            dataRow.createCell(5).setCellValue(echantillon.getObservation());
            //dataRow.createCell(6).setCellValue(echantillon.getEtatEchantillon().toString());

            dataRowIndex++;
        }

        ServletOutputStream ops = response.getOutputStream();
        workbook.write(ops);
        workbook.close();
        ops.close();

    }
}
