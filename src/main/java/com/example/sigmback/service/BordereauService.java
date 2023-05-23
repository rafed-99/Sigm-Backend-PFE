package com.example.sigmback.service;

import com.example.sigmback.model.Bordereau;
import com.example.sigmback.repository.IArchiveRepository;
import com.example.sigmback.repository.IBordereauRepository;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.*;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class BordereauService implements IBordereauService{

    @Autowired
    IBordereauRepository iBordereauRepository;

    @Autowired
    IArchiveRepository iArchiveRepository;

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

    public List<Bordereau> retrieveBordereauByArchive(Long idArchive){
        return iArchiveRepository.findById(idArchive).get().getBordereaux();
    }

    public void generateExcelBordereau(HttpServletResponse response) throws Exception {

        List<Bordereau> bordereaux = iBordereauRepository.findAll();
        //List<Geologie> geologies = iGeologieRepository.findAll();

        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("Layers");
        HSSFRow row = sheet.createRow(0);

        row.createCell(0).setCellValue("Id Receipt");
        row.createCell(1).setCellValue("Receipt Code");
        row.createCell(2).setCellValue("Receipt Date");
        row.createCell(3).setCellValue("Asked Analysis");
        row.createCell(4).setCellValue("Requirements");
        row.createCell(5).setCellValue("Urgency");

        int dataRowIndex = 1;

        for (Bordereau bordereau : bordereaux) {
            HSSFRow dataRow = sheet.createRow(dataRowIndex);
            //dataRow.createCell(0).setCellValue(geologie.getPoint().getHoleId());
            dataRow.createCell(0).setCellValue(bordereau.getBordereauId());
            dataRow.createCell(1).setCellValue(bordereau.getBordereauCode());
            dataRow.createCell(2).setCellValue(bordereau.getDateEnvoi().toString());
            dataRow.createCell(3).setCellValue(bordereau.getAnalyseDemande());
            dataRow.createCell(4).setCellValue(bordereau.getExigences());
            dataRow.createCell(5).setCellValue(bordereau.getUrgences().toString());

            dataRowIndex++;
        }

        ServletOutputStream ops = response.getOutputStream();
        workbook.write(ops);
        workbook.close();
        ops.close();

    }
}
