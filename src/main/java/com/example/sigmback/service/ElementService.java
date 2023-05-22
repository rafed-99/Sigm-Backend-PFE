package com.example.sigmback.service;

import com.example.sigmback.model.Couche;
import com.example.sigmback.model.Element;
import com.example.sigmback.repository.IElementRepository;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ElementService implements IElementService{

    @Autowired
    IElementRepository iElementRepository;

    @Override
    public Element addElement(Element element) {

        return iElementRepository.save(element);
    }

    @Override
    public Element updateElement(Element element) {

        return iElementRepository.save(element);
    }

    @Override
    public void deleteElement(Long id_element) {

        iElementRepository.deleteById(id_element);
    }

    @Override
    public List<Element> retrieveElements() {

        return (List<Element>)iElementRepository.findAll();
    }

    @Override
    public Element retrieveOneElement(Long id_element) {

        return iElementRepository.findById(id_element).get();
    }

    public void generateExcelElement(HttpServletResponse response) throws Exception {

        List<Element> elements = iElementRepository.findAll();
        //List<Geologie> geologies = iGeologieRepository.findAll();

        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("Layers");
        HSSFRow row = sheet.createRow(0);

        row.createCell(0).setCellValue("Id Element");
        row.createCell(1).setCellValue("Element Code");
        row.createCell(2).setCellValue("Element Name");

        int dataRowIndex = 1;

        for (Element element : elements) {
            HSSFRow dataRow = sheet.createRow(dataRowIndex);
            //dataRow.createCell(0).setCellValue(geologie.getPoint().getHoleId());
            dataRow.createCell(0).setCellValue(element.getElementId());
            dataRow.createCell(1).setCellValue(element.getElementCode());
            dataRow.createCell(2).setCellValue(element.getElementLibelle());

            dataRowIndex++;
        }

        ServletOutputStream ops = response.getOutputStream();
        workbook.write(ops);
        workbook.close();
        ops.close();

    }
}
