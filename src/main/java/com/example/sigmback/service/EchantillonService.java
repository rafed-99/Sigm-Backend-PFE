package com.example.sigmback.service;

import com.example.sigmback.model.*;
import com.example.sigmback.repository.*;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.RichTextString;
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

    @Autowired
    IElementRepository iElementRepository;

    @Autowired
    IAnalyseRepository iAnalyseRepository;

    private List<Element> elements;

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

    public void generateExcelElement(HttpServletResponse response , Long id_point) throws Exception {

        List<Echantillon> echantillons = retrieveEchantillonByPoint(id_point);
        System.out.println(echantillons);
        elements = iElementRepository.findAll();
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

        Integer a = elements.size();
        System.out.println("elements lenght "+a);
        Integer j=6;

            for(Integer i=0;i<a;i++){
                /*System.out.println("J antering the loop "+j);
                System.out.println("code element "+elements.get(i).getElementCode());*/
                //System.out.println("J+I "+(j+i));
                row.createCell(j).setCellValue(elements.get(i).getElementCode());
                //System.out.println("j before adding "+j);
                j++;
                //System.out.println("j after adding  "+j);
            }


        Integer k=6;


        int dataRowIndex = 1;


        for (Echantillon echantillon : echantillons) {
            HSSFRow dataRow = sheet.createRow(dataRowIndex);
            //dataRow.createCell(0).setCellValue(geologie.getPoint().getHoleId());
            dataRow.createCell(0).setCellValue(echantillon.getEchantillonId());
            dataRow.createCell(1).setCellValue(echantillon.getGeologie().getCouche().getCoucheCode());
            dataRow.createCell(2).setCellValue(echantillon.getDepthFrom());
            dataRow.createCell(3).setCellValue(echantillon.getDepthTo());
            if(echantillon.getPuissanceReelle()==null){
                dataRow.createCell(4).setCellValue("");
            }else{
                dataRow.createCell(4).setCellValue(echantillon.getPuissanceReelle());
            }
            dataRow.createCell(5).setCellValue(echantillon.getObservation());
            //dataRow.createCell(6).setCellValue(echantillon.getEtatEchantillon().toString());


            System.out.println("k "+k);
            System.out.println("a "+a);




                /*for(Integer i=0;i<a;i++){

                    List<Analyses> analyses = iEchantillonRepository.findById(echantillon.getEchantillonId()).get().getAnalyses();
                    for(Integer z=0;z<analyses.size();z++) {

                        if (analyses.get(z) != null) {

                            String condition = sheet.getRow(0).getCell(k).toString();

                            if ((analyses.get(z).getElement()).getElementCode() == condition) {
                                System.out.println(analyses.get(z).getValeurAnalyse());
                                dataRow.createCell(k+i).setCellValue(analyses.get(z).getValeurAnalyse().toString());

                                k++;

                            } else {
                                dataRow.createCell(k +i).setCellValue("");
                                System.out.println("****** " + (k+i));
                                //k++;

                            }
                        } else {
                            dataRow.createCell(k+i).setCellValue("");
                            System.out.println("+++++++ " + (k+i));
                            //k++;

                        }
                    }

                }*/

            for(Integer i=0;i<a;i++){
                dataRow.createCell(k+i).setCellValue("");
            }

            List<Analyses> analyses = iEchantillonRepository.findById(echantillon.getEchantillonId()).get().getAnalyses();

            for(Integer i=0; i<analyses.size();i++){
                String element= analyses.get(i).getElement().getElementCode();
                Integer index=getIndexfromElement(element)+k;
                dataRow.getCell(index).setCellValue(analyses.get(i).getValeurAnalyse().toString());
            }





            dataRowIndex++;
        }

        ServletOutputStream ops = response.getOutputStream();
        workbook.write(ops);
        workbook.close();
        ops.close();

    }

    Integer getIndexfromElement(String elementCode){
        Integer index = 0;
        for(Integer i=0; i<elements.size();i++){
          if(elements.get(i).getElementCode()==elementCode){
              index = i;
          }
        }
        return index;
    }

    public File exportExistingReport(Long idBordereau) throws FileNotFoundException, JRException {

        Bordereau bordereau = iBordereauRepository.findById(idBordereau).orElse(null);


        System.out.println("bordereau : "+bordereau.getBordereauId());


        //String path = "D:\\ReportSigm\\";

        String path = "D:\\ReportSigm";
        //load file and compile it
        //File file = ResourceUtils.getFile("classpath:bordereaux.jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(getClass().getResourceAsStream("/report/bordereau1.jrxml"));
        //JasperReport jasperReport = JasperCompileManager.compileReport(getClass().getResourceAsStream("bordereau1.jrxml"));

        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(bordereau.getEchantillons());
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
}
