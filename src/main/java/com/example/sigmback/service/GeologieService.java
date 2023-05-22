package com.example.sigmback.service;

import com.example.sigmback.model.*;
import com.example.sigmback.repository.ICoucheRepository;
import com.example.sigmback.repository.IGeologieRepository;
import com.example.sigmback.repository.IPointRepository;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GeologieService implements IGeologieService{

    @Autowired
    IGeologieRepository iGeologieRepository;

    @Autowired
    IPointRepository iPointRepository;

    @Autowired
    ICoucheRepository iCoucheRepository;


    @Override
    public Geologie addGeologie(Geologie geologie) {

        Float dTo = geologie.getDepthTo();
        Float dFrom = geologie.getDepthFrom();
        geologie.setPuissance(dTo-dFrom) ;
        System.out.println("puissance: "+geologie.getPuissance());
        return iGeologieRepository.save(geologie);
    }



    @Override
    public Geologie updateGeologie(Geologie geologie) {

        geologie.setDepthTo(geologie.getDepthTo());
        geologie.setDepthFrom(geologie.getDepthFrom());
        Float dTo = geologie.getDepthTo();
        Float dFrom = geologie.getDepthFrom();
        geologie.setPuissance(dTo-dFrom);
        return iGeologieRepository.save(geologie);
    }



    @Override
    public void deleteGeologie(Long id_geologie) {

        iGeologieRepository.deleteById(id_geologie);
    }

    @Override
    public List<Geologie> retrieveGeologies() {

        return (List<Geologie>)iGeologieRepository.findAll();
    }

    @Override
    public Geologie retrieveOneGeologie(Long id_geologie) {

        return iGeologieRepository.findById(id_geologie).get();
    }

    @Override
    public List<Geologie> retrieveGeologieByPoint(long id_point) {
        return iPointRepository.findById(id_point).get().getGeologies();
    }

    public Geologie addwithaffectation (Long id_point, Long id_couche, Geologie geologie){
        Point point = iPointRepository.findById(id_point).orElse(null);
        Couche couche = iCoucheRepository.findById(id_couche).orElse(null);
        geologie.setPoint(point);
        geologie.setCouche(couche);
        Float dTo = geologie.getDepthTo();
        Float dFrom = geologie.getDepthFrom();
        geologie.setPuissance(dTo-dFrom) ;
        System.out.println("puissance: "+geologie.getPuissance());
        return iGeologieRepository.save(geologie);
    }

    public void generateExcelGeologie(HttpServletResponse response , Long pointId) throws Exception {

        List<Geologie> geologies = iPointRepository.findById(pointId).get().getGeologies();
        //List<Geologie> geologies = iGeologieRepository.findAll();

        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("Courses Info");
        HSSFRow row = sheet.createRow(0);

        row.createCell(0).setCellValue("Layer");
        row.createCell(1).setCellValue("Id Geology");
        row.createCell(2).setCellValue("Depth From");
        row.createCell(3).setCellValue("Depth To");
        row.createCell(4).setCellValue("Volume");
        row.createCell(5).setCellValue("Real Volume");
        row.createCell(6).setCellValue("Lithological Description");



        int dataRowIndex = 1;

        for (Geologie geologie : geologies) {
            HSSFRow dataRow = sheet.createRow(dataRowIndex);
            //dataRow.createCell(0).setCellValue(geologie.getPoint().getHoleId());
            dataRow.createCell(0).setCellValue(geologie.getCouche().getCoucheCode());
            dataRow.createCell(1).setCellValue(geologie.getGeologieId());
            dataRow.createCell(2).setCellValue(geologie.getDepthFrom());
            dataRow.createCell(3).setCellValue(geologie.getDepthTo());
            dataRow.createCell(4).setCellValue(geologie.getPuissance());
            dataRow.createCell(5).setCellValue(geologie.getPuissanceReelle());
            dataRow.createCell(6).setCellValue(geologie.getDescriptionLithologique());

            dataRowIndex++;
        }

        ServletOutputStream ops = response.getOutputStream();
        workbook.write(ops);
        workbook.close();
        ops.close();

    }

}
