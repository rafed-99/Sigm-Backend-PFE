package com.example.sigmback.service;

import com.example.sigmback.model.Archive;
import com.example.sigmback.model.Couche;
import com.example.sigmback.repository.IArchiveRepository;
import com.example.sigmback.repository.IPointRepository;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.util.CellRangeAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArchiveService implements IArchiveService{

    @Autowired
    IArchiveRepository iArchiveRepository;

    @Autowired
    IPointRepository iPointRepository;

    Integer pointSize;
    Integer bordereauSize;

    @Override
    public Archive addArchive(Archive archive) {
        return iArchiveRepository.save(archive);
    }

    @Override
    public Archive updateArchive(Archive archive) {
        return iArchiveRepository.save(archive);
    }

    @Override
    public void deleteArchive(Long id_archive) {
        iArchiveRepository.deleteById(id_archive);
    }

    @Override
    public List<Archive> retrieveArchive() {
        return iArchiveRepository.findAll();
    }

    @Override
    public Archive retrieveOneArchive(Long id_archive) {
        return iArchiveRepository.findById(id_archive).get();
    }

    public void generateExcelArchivePoint(HttpServletResponse response) throws Exception {

        List<Archive> archives = iArchiveRepository.findAll();
        Integer pointSize =0;
        Integer bordereauSize =0;
        HSSFWorkbook workbook = new HSSFWorkbook();

        HSSFSheet sheet = workbook.createSheet("Layers");
        HSSFRow row = sheet.createRow(0);

        row.createCell(0).setCellValue("Id Archive");
        row.createCell(1).setCellValue("Archive Name");
        row.createCell(2).setCellValue("Archive Date");
        row.createCell(3).setCellValue("Archive Type");

        int dataRowIndex = 1;

        for (Archive archive : archives) {
            HSSFRow dataRow = sheet.createRow(dataRowIndex);
            //dataRow.createCell(0).setCellValue(geologie.getPoint().getHoleId());
            if(archive.getArchiveType().toString() == "Point"){

            dataRow.createCell(0).setCellValue(archive.getArchiveId());
            dataRow.createCell(1).setCellValue(archive.getArchiveLibelle());
            dataRow.createCell(2).setCellValue(archive.getArchiveDate().toString());
            dataRow.createCell(3).setCellValue(archive.getArchiveType().toString());

                dataRowIndex++;
            }



        }

        ServletOutputStream ops = response.getOutputStream();
        workbook.write(ops);
        workbook.close();
        ops.close();

    }

    public void generateExcelArchiveBordereau(HttpServletResponse response) throws Exception {

        List<Archive> archives = iArchiveRepository.findAll();

        HSSFWorkbook workbook = new HSSFWorkbook();

        HSSFSheet sheet = workbook.createSheet("Layers");
        HSSFRow row = sheet.createRow(0);

        row.createCell(0).setCellValue("Id Archive");
        row.createCell(1).setCellValue("Archive Name");
        row.createCell(2).setCellValue("Archive Date");
        row.createCell(3).setCellValue("Archive Type");

        int dataRowIndex = 1;

        for (Archive archive : archives) {
            HSSFRow dataRow = sheet.createRow(dataRowIndex);
            //dataRow.createCell(0).setCellValue(geologie.getPoint().getHoleId());

            if(archive.getArchiveType().toString() == "Bordereau"){

                    dataRow.createCell(0).setCellValue(archive.getArchiveId());
                    dataRow.createCell(1).setCellValue(archive.getArchiveLibelle());
                    dataRow.createCell(2).setCellValue(archive.getArchiveDate());
                    dataRow.createCell(3).setCellValue(archive.getArchiveType().toString());

                dataRowIndex++;
            }


        }

        ServletOutputStream ops = response.getOutputStream();
        workbook.write(ops);
        workbook.close();
        ops.close();

    }
}
