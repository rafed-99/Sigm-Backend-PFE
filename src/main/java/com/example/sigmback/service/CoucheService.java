package com.example.sigmback.service;

import com.example.sigmback.model.Couche;
import com.example.sigmback.model.Geologie;
import com.example.sigmback.repository.ICoucheRepository;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CoucheService implements ICoucheService{

    @Autowired
    ICoucheRepository iCoucheRepository;

    @Override
    public Couche addCouche(Couche couche) {
        return iCoucheRepository.save(couche);
    }

    @Override
    public Couche updateCouche(Couche couche) {
        return iCoucheRepository.save(couche);
    }

    @Override
    public void deleteCouche(Long id_couche) {
        iCoucheRepository.deleteById(id_couche);
    }

    @Override
    public List<Couche> retrieveCouches() {
        return (List<Couche>) iCoucheRepository.findAll();
    }

    @Override
    public Couche retrieveOneCouche(Long id_couche) {
        return iCoucheRepository.findById(id_couche).get();
    }

    public void generateExcelCouche(HttpServletResponse response) throws Exception {

        List<Couche> couches = iCoucheRepository.findAll();
        //List<Geologie> geologies = iGeologieRepository.findAll();

        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("Layers");
        HSSFRow row = sheet.createRow(0);

        row.createCell(0).setCellValue("Id Layer");
        row.createCell(1).setCellValue("Layer Code");
        row.createCell(2).setCellValue("Layer Name");
        row.createCell(3).setCellValue("Layer Order");

        int dataRowIndex = 1;

        for (Couche couche : couches) {
            HSSFRow dataRow = sheet.createRow(dataRowIndex);
            //dataRow.createCell(0).setCellValue(geologie.getPoint().getHoleId());
            dataRow.createCell(0).setCellValue(couche.getCoucheId());
            dataRow.createCell(1).setCellValue(couche.getCoucheCode());
            dataRow.createCell(2).setCellValue(couche.getCoucheLibelle());
            dataRow.createCell(3).setCellValue(couche.getCoucheOrdre());

            dataRowIndex++;
        }

        ServletOutputStream ops = response.getOutputStream();
        workbook.write(ops);
        workbook.close();
        ops.close();

    }
}
