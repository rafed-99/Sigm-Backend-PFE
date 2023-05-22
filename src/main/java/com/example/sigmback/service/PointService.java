package com.example.sigmback.service;

import com.example.sigmback.model.*;
import com.example.sigmback.repository.IArchiveRepository;
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
public class PointService implements IPointService{

    @Autowired
    IPointRepository iPointRepository;

    @Autowired
    IGisementRepository iGisementRepository;

    @Autowired
    IArchiveRepository iArchiveRepository;

    @Override
    public Point addPoint(Point point) {

        return iPointRepository.save(point);
    }

    @Override
    public Point updatePoint(Point point) {

        return iPointRepository.save(point);
    }

    @Override
    public void deletePoint(Long id_point) {
        iPointRepository.deleteById(id_point);
    }

    @Override
    public List<Point> retrievePoints() {

        return (List<Point>)iPointRepository.findAll();
    }

    @Override
    public Point retrieveOnePoint(Long id_point) {

        return iPointRepository.findById(id_point).get();
    }

    @Override
    public List<Point> retrievePointsByGisement(Long id_gisement) {

        return iGisementRepository.findById(id_gisement).get().getPoints();
    }

    @Override
    public List<Point> retrieveArchiveByPoint(Long id_archive){
        return iArchiveRepository.findById(id_archive).get().getPoints();
    }

    public Point addwithaffectation (Long id_gisement, Point point){
        Gisement gisement = iGisementRepository.findById(id_gisement).orElse(null);
        point.setGisement(gisement);
        return iPointRepository.save(point);
    }

    public Point addToArchive(Long id_point, Long id_archive){
        Archive archive = iArchiveRepository.findById(id_archive).orElse(null);
        Point point = iPointRepository.findById(id_point).orElse(null);
        point.setArchive(archive);
        return iPointRepository.save(point);
    }

    public void generateExcelPoint(HttpServletResponse response) throws Exception {

        List<Point> points = iPointRepository.findAll();

        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("Courses Info");
        HSSFRow row = sheet.createRow(0);

        row.createCell(0).setCellValue("Id Point");
        row.createCell(1).setCellValue("Coordinate X");
        row.createCell(2).setCellValue("Coordinate Y");
        row.createCell(3).setCellValue("Coordinate Z");
        row.createCell(4).setCellValue("Coordinate System");
        row.createCell(5).setCellValue("Coordinate X Converted");
        row.createCell(6).setCellValue("Coordinate Y Converted");
        row.createCell(7).setCellValue("Coordinate Z Converted");
        row.createCell(8).setCellValue("Coordinate Z Converted");
        row.createCell(9).setCellValue("Azimut");
        row.createCell(10).setCellValue("Depth Max");
        //row.createCell(11).setCellValue("Export Gis");
        row.createCell(11).setCellValue("Dip");
        row.createCell(12).setCellValue("Point Type");
        row.createCell(13).setCellValue("Field Name");
        row.createCell(14).setCellValue("Field Sector");



        int dataRowIndex = 1;

        for (Point point : points) {
            HSSFRow dataRow = sheet.createRow(dataRowIndex);
            dataRow.createCell(0).setCellValue(point.getPointId());
            dataRow.createCell(1).setCellValue(point.getX());
            dataRow.createCell(2).setCellValue(point.getY());
            dataRow.createCell(3).setCellValue(point.getZ());
            dataRow.createCell(4).setCellValue(point.getSystemeCoordonnees().toString());
            dataRow.createCell(5).setCellValue(point.getXC());
            dataRow.createCell(6).setCellValue(point.getYC());
            dataRow.createCell(7).setCellValue(point.getZC());
            dataRow.createCell(8).setCellValue(point.getDip());
            dataRow.createCell(9).setCellValue(point.getAzimut());
            dataRow.createCell(10).setCellValue(point.getDepthMax());
            //dataRow.createCell(11).setCellValue(point.getExportGis().toString());
            dataRow.createCell(11).setCellValue(point.getNiveauPizometrique());
            dataRow.createCell(12).setCellValue(point.getPointType().toString());
            dataRow.createCell(13).setCellValue(point.getGisement().getGisementLibelle());
            dataRow.createCell(14).setCellValue(point.getGisement().getSecteur().toString());


            dataRowIndex++;
        }

        ServletOutputStream ops = response.getOutputStream();
        workbook.write(ops);
        workbook.close();
        ops.close();

    }
}
