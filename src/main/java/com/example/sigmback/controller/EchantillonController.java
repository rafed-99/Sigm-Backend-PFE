package com.example.sigmback.controller;

import com.example.sigmback.model.Echantillon;
import com.example.sigmback.service.EchantillonService;
import jakarta.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.io.*;
import java.util.Arrays;
import java.util.List;

@RestController
//@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/echantillon")
@PreAuthorize("hasAnyRole('CENTRE_ADMIN','CENTRE_USER','CENTRE_CONFIRM','GEOLOGIE_ADMIN','GEOLOGIE_USER','GEOLOGIE_CONSULT')")
public class EchantillonController {

    @Autowired
    EchantillonService echantillonService;

    // http://localhost:8099/api/echantillon/addechantillon
    @PostMapping("/addechantillon")
    @PreAuthorize("hasAnyAuthority('geologieadmin:create','geologieuser:create')")
    public Echantillon saveEchantillon(@RequestBody Echantillon echantillon){

        return echantillonService.addEchantillon(echantillon);
    }

    // http://localhost:8099/api/echantillon/updateechantillon
    @PutMapping("/updateechantillon")
    @PreAuthorize("hasAnyAuthority('geologieadmin:update','geologieuser:update')")
    public Echantillon modifyEchantillon(@RequestBody Echantillon echantillon){

        return echantillonService.updateEchantillon(echantillon);
    }

    // http://localhost:8099/api/echantillon/deleteechantillon/{id_echantillon}
    @DeleteMapping("/deleteechantillon/{id_echantillon}")
    @PreAuthorize("hasAnyAuthority('geologieadmin:delete')")
    public void eraseEchantillon(@PathVariable("id_echantillon") Long id_echantillon){

        echantillonService.deleteEchantillon(id_echantillon);
    }

    // http://localhost:8099/api/echantillon/showechantillons
    @GetMapping("/showechantillons")
    @PreAuthorize("hasAnyAuthority('centreadmin:read','centreuser:read','centreconfirm:read','geologieadmin:read','geologieuser:read','geologieconsult:read')")
    public List<Echantillon> showEchantillons(){

        return echantillonService.retrieveEchantillons();
    }

    // http://localhost:8099/api/echantillon/showechantillon/{id_echantillon}
    @GetMapping("/showechantillon/{id_echantillon}")
    @PreAuthorize("hasAnyAuthority('centreadmin:read','centreuser:read','centreconfirm:read','geologieadmin:read','geologieuser:read','geologieconsult:read')")
    public Echantillon showOneEchantillon(@PathVariable("id_echantillon") Long id_echantillon){

        return echantillonService.retrieveOneEchantillon(id_echantillon);
    }

    // http://localhost:8099/api/echantillon/showechantillonsbypoint/{id_point}
    @GetMapping("/showechantillonsbypoint/{id_point}")
    @PreAuthorize("hasAnyAuthority('centreadmin:read','centreuser:read','centreconfirm:read','geologieadmin:read','geologieuser:read','geologieconsult:read')")
    public List<Echantillon> show(@PathVariable("id_point") Long id_point){
        return echantillonService.retrieveEchantillonByPoint(id_point);
    }

    // http://localhost:8099/api/echantillon/showechantillonsbygeologie/{id_geologie}
    @GetMapping("/showechantillonsbygeologie/{id_geologie}")
    @PreAuthorize("hasAnyAuthority('centreadmin:read','centreuser:read','centreconfirm:read','geologieadmin:read','geologieuser:read','geologieconsult:read')")
    public List<Echantillon> showEchantillonsByGeologie(@PathVariable("id_geologie") Long id_geologie){
        return echantillonService.retrieveEchantillonsByGeologie(id_geologie);
    }

    // http://localhost:8099/api/echantillon/showechantillonbybordereau/{id_bordereau}
    @GetMapping("/showechantillonbybordereau/{id_bordereau}")
    @PreAuthorize("hasAnyAuthority('centreadmin:read','centreuser:read','centreconfirm:read','geologieadmin:read','geologieuser:read','geologieconsult:read')")
    public List<Echantillon> showEchantillonByBordereau(@PathVariable("id_bordereau") Long id_bordereau){

        return echantillonService.retrieveEchantillonByBordereau(id_bordereau);
    }

    // http://localhost:8099/api/echantillon/addaffect/{id_geologie}
    @PostMapping("/addaffect/{id_geologie}")
    @PreAuthorize("hasAnyAuthority('geologieadmin:create','geologieuser:create')")

    public Echantillon addAffect(@RequestBody Echantillon echantillon, @PathVariable(name = "id_geologie")  Long id_geologie){

        return echantillonService.addWithIdGeologie(id_geologie,echantillon);
    }


    @PostMapping(value = "/bordereau/{idBordereau}/report",produces = "application/pdf")
    @PreAuthorize("hasAnyAuthority('geologieadmin:create','geologieuser:create')")
    @ResponseBody
    public ResponseEntity<byte[]> getBorderauEchantillon(@RequestBody List<Echantillon> selectedEchantillons, @PathVariable Long idBordereau) throws IOException, JRException {


        File file = echantillonService.exportReport(selectedEchantillons,idBordereau);
        FileInputStream fis = new FileInputStream(file);
        byte[] buffer = new byte[8192];
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        int bytesRead;
        while ((bytesRead = fis.read(buffer)) != -1) {
            baos.write(buffer, 0, bytesRead);
        }
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        //headers.add("Content-Disposition", "inline; =" + file.getName());
        //headers.add("", "inline; =" + file.getName());
        ResponseEntity<byte[]> response = new ResponseEntity<byte[]>(baos.toByteArray(), headers, HttpStatus.OK);
        fis.close();
        return response;
    }

    /*@PostMapping("/bordereau/{idBordereau}/report")
    @ResponseBody
    public File generateReport(@RequestBody List<Echantillon> selectedEchantillons, @PathVariable Long idBordereau) throws FileNotFoundException, JRException, FileNotFoundException {
        return echantillonService.exportReport(selectedEchantillons,idBordereau);
    }*/

    // http://localhost:8099/api/echantillon/exportexcelechantillon/{id_geologie}
    @GetMapping("/exportexcelechantillon/{id_geologie}")
    @PreAuthorize("hasAnyAuthority('geologieadmin:read','geologieuser:read','geologieconsult:read')")
    public void generateExcelReport(HttpServletResponse response ,@PathVariable("id_geologie") Long id_geologie ) throws Exception{

        response.setContentType("application/octet-stream");

        String headerKey = "Content-Disposition";
        String headerValue = "attachment;filename=samples.xls";

        response.setHeader(headerKey, headerValue);

        echantillonService.generateExcelElement(response,id_geologie);

        response.flushBuffer();
    }

    //http://localhost:8099/api/echantillon/bordereau/report/{idBordereau}
    @GetMapping(value = "/bordereau/report/{idBordereau}",produces = "application/pdf")
    @ResponseBody
    @PreAuthorize("hasAnyAuthority('geologieadmin:read','geologieuser:read','geologieconsult:read','centreadmin:read','centreuser:read','centreconfirm:read')")
    public ResponseEntity<byte[]> getBorderauEchantillonById(@PathVariable Long idBordereau) throws IOException, JRException {


        File file = echantillonService.exportExistingReport(idBordereau);
        FileInputStream fis = new FileInputStream(file);
        byte[] buffer = new byte[8192];
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        int bytesRead;
        while ((bytesRead = fis.read(buffer)) != -1) {
            baos.write(buffer, 0, bytesRead);
        }
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        //headers.add("Content-Disposition", "inline; =" + file.getName());
        //headers.add("", "inline; =" + file.getName());
        ResponseEntity<byte[]> response = new ResponseEntity<byte[]>(baos.toByteArray(), headers, HttpStatus.OK);
        fis.close();
        return response;
    }

    @GetMapping("/counttoverify")
    @PreAuthorize("hasAnyAuthority('geologieadmin:read','geologieuser:read','geologieconsult:read')")
    public Long countToVerify(){

        return echantillonService.countToVerifySample();
    }

    @GetMapping("/countsent")
    @PreAuthorize("hasAnyAuthority('geologieadmin:read','geologieuser:read','geologieconsult:read')")
    public Long countSent(){

        return echantillonService.countSentSample();
    }

    @GetMapping("/countreceived")
    @PreAuthorize("hasAnyAuthority('geologieadmin:read','geologieuser:read','geologieconsult:read')")
    public Long countReceived(){

        return echantillonService.countReceivedSample();
    }

    @GetMapping("/countanalysed")
    @PreAuthorize("hasAnyAuthority('geologieadmin:read','geologieuser:read','geologieconsult:read')")
    public Long countAnalysed(){

        return echantillonService.countAnalysedSample();
    }
}
