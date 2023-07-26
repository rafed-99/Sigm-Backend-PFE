package com.example.sigmback.controller;

import com.example.sigmback.model.Bordereau;
import com.example.sigmback.service.BordereauService;
import jakarta.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/bordereau")
@PreAuthorize("hasAnyRole('CENTRE_ADMIN','CENTRE_USER','CENTRE_CONFIRM','GEOLOGIE_ADMIN','GEOLOGIE_USER')")
public class BordereauController {

    @Autowired
    BordereauService bordereauService;
    private HttpServletResponse response;

    // http://localhost:8099/api/bordereau/addbordereau
    @PostMapping("/addbordereau")
    @PreAuthorize("hasAnyAuthority('geologieadmin:create','geologieuser:create')")
    public Bordereau saveBordereau(@RequestBody Bordereau bordereau) throws JRException, IOException {

        return bordereauService.addBordereau(bordereau);
    }

    /*@GetMapping("exportbordereau")
    public String export(Bordereau b) throws JRException, FileNotFoundException {
        return bordereauService.exportPdf(b);
    }*/





    // http://localhost:8099/api/bordereau/updatebordereau
    @PutMapping("/updatebordereau")
    @PreAuthorize("hasAnyAuthority('centreadmin:read','centreuser:read','centreconfirm:read')")
    public Bordereau modifyBordereau(@RequestBody Bordereau bordereau){

        return bordereauService.updateBordereau(bordereau);
    }

    // http://localhost:8099/api/bordereau/deletebordereau/{id_bordereau}
    @DeleteMapping("/deletebordereau/{id_bordereau}")
    @PreAuthorize("hasAnyAuthority('centreadmin:delete','centreuser:delete','centreconfirm:delete')")
    public void eraseBordereau(@PathVariable("id_bordereau") Long id_bordereau){

        bordereauService.deleteBordereau(id_bordereau);
    }

    // http://localhost:8099/api/bordereau/showbordereaux
    @GetMapping("/showbordereaux")
    @PreAuthorize("hasAnyAuthority('centreadmin:read','centreuser:read','centreconfirm:read')")
    public List<Bordereau> showBordereaux(){

        return bordereauService.retrieveBordereaux();
    }

    // http://localhost:8099/api/bordereau/showbordereau/{id_bordereau}
    @GetMapping("/showbordereau/{id_bordereau}")
    @PreAuthorize("hasAnyAuthority('centreadmin:read','centreuser:read','centreconfirm:read')")
    public Bordereau showOneBordereau(@PathVariable("id_bordereau") Long id_bordereau){

        return bordereauService.retrieveOneBordereau(id_bordereau);
    }

    @PutMapping("/updatebord")
    @PreAuthorize("hasAnyAuthority('centreadmin:read','centreuser:read','centreconfirm:read')")
    public void modifyBordereaux(@RequestBody List<Bordereau> bordereau){
        bordereauService.updateBordereau1(bordereau);

    }

    //http://localhost:8099/api/bordereau/getbordereaubyarchive/{idArchive}
    @GetMapping("/getbordereaubyarchive/{idArchive}")
    @PreAuthorize("hasAnyAuthority('centreadmin:read','centreuser:read','centreconfirm:read')")
    public List<Bordereau> retrieveBordereausByArchive(@PathVariable("idArchive")Long idArchive){
        return bordereauService.retrieveBordereauByArchive(idArchive);
    }

    // http://localhost:8099/api/bordereau/exportexcelbordereau
    @GetMapping("/exportexcelbordereau")
    @PreAuthorize("hasAnyAuthority('centreadmin:read','centreuser:read','centreconfirm:read')")
    public void generateExcelReport(HttpServletResponse response) throws Exception{

        response.setContentType("application/octet-stream");

        String headerKey = "Content-Disposition";
        String headerValue = "attachment;filename=receipts.xls";

        response.setHeader(headerKey, headerValue);

        bordereauService.generateExcelBordereau(response);

        response.flushBuffer();
    }

    @GetMapping("/retrieveBordereauByEchantillon/{idEchantillon}")
    @PreAuthorize("hasAnyAuthority('centreadmin:read','centreuser:read','centreconfirm:read')")
    public Bordereau retrieveByEchantillon(@PathVariable("idEchantillon")Long idEchantillon){
        return bordereauService.retrieveBordereauByEchantillon(idEchantillon);
    }

}
