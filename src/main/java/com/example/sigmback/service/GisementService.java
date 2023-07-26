package com.example.sigmback.service;

import com.example.sigmback.model.Couche;
import com.example.sigmback.model.Gisement;
import com.example.sigmback.model.Point;
import com.example.sigmback.repository.IGisementRepository;
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
public class GisementService implements IGisementService{

    @Autowired
    IGisementRepository iGisementRepository;

    @Autowired
    IPointRepository iPointRepository;
    @Override
    public Gisement addGisement(Gisement g) {

        return iGisementRepository.save(g);
    }

    @Override
    public void deleteGisement(Long id) {

        iGisementRepository.deleteById(id);
    }

    @Override
    public Gisement updateGisement(Gisement g) {

        return iGisementRepository.save(g);
    }

    @Override
    public List<Gisement> retrieveGisement() {

        return (List<Gisement>) iGisementRepository.findAll();
    }

    @Override
    public Gisement retrieveOneGisement(Long id) {

        return iGisementRepository.findById(id).get();
    }


    @Override
    public List<Gisement> findGisementsBySecteur(String secteur) {

        return iGisementRepository.findGisementsBySecteur(secteur);
    }

    @Override
    public Long countGisementBySecteur(String secteur) {

        return iGisementRepository.countGisementBySecteur(secteur);
    }

    /*@Override
    public List<Gisement> orderGisementBySecteurAsc() {

        return iGisementRepository.orderGisementBySecteurAsc();
    }

    @Override
    public List<Gisement> orderGisementBySecteurDesc() {

        return iGisementRepository.orderGisementBySecteurDesc();
    }*/

    /*@Override
    public List<Gisement> searchByLibelle(String libelle) {
        if(libelle != null) {
            return iGisementRepository.searchByLibelle(libelle);
        }
        return iGisementRepository.findAll();
    }*/

    public void generateExcelGisement(HttpServletResponse response) throws Exception {

        List<Gisement> gisements = iGisementRepository.findAll();
        //List<Geologie> geologies = iGeologieRepository.findAll();

        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("Layers");
        HSSFRow row = sheet.createRow(0);

        row.createCell(0).setCellValue("Id FIeld");
        row.createCell(1).setCellValue("Field Code");
        row.createCell(2).setCellValue("Field Name");
        row.createCell(3).setCellValue("Field Sector");

        int dataRowIndex = 1;

        for (Gisement gisement : gisements) {
            HSSFRow dataRow = sheet.createRow(dataRowIndex);
            //dataRow.createCell(0).setCellValue(geologie.getPoint().getHoleId());
            dataRow.createCell(0).setCellValue(gisement.getGisementId());
            dataRow.createCell(1).setCellValue(gisement.getGisementCode());
            dataRow.createCell(2).setCellValue(gisement.getGisementLibelle());
            dataRow.createCell(3).setCellValue(gisement.getSecteur().toString());

            dataRowIndex++;
        }

        ServletOutputStream ops = response.getOutputStream();
        workbook.write(ops);
        workbook.close();
        ops.close();

    }

    public List<Object[]> c (){
        return iGisementRepository.countbysecteur();
    }


}
