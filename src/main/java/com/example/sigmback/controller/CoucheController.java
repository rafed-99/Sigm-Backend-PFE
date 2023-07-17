package com.example.sigmback.controller;

import com.example.sigmback.model.Couche;
import com.example.sigmback.service.CoucheService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/geologie/couche")
@PreAuthorize("hasAnyRole('GEOLOGIE_ADMIN','GEOLOGIE_USER','GEOLOGIE_CONSULT')")
public class CoucheController {

    @Autowired
    CoucheService coucheService;

    // http://localhost:8099/api/couche/addcouche
    @PostMapping("/addcouche")
    @PreAuthorize("hasAnyAuthority('geologieadmin:create','geologieuser:create')")
    public Couche saveCouche(@RequestBody Couche couche){

        return coucheService.addCouche(couche);
    }

    // http://localhost:8099/api/couche/updatecouche
    @PutMapping("/updatecouche")
    @PreAuthorize("hasAnyAuthority('geologieadmin:update','geologieuser:update')")
    public Couche modifyGisement(@RequestBody Couche couche){

        return coucheService.updateCouche(couche);
    }

    // http://localhost:8099/api/couche/deletecouche/{id_couche}
    @DeleteMapping("/deletecouche/{id_couche}")
    @PreAuthorize("hasAnyAuthority('geologieadmin:delete')")
    public void eraseGisement(@PathVariable("id_couche") Long id_couche){

        coucheService.deleteCouche(id_couche);
    }

    // http://localhost:8099/api/couche/showcouches
    @GetMapping("/showcouches")
    @PreAuthorize("hasAnyAuthority('geologieadmin:read','geologieuser:read','geologieconsult:read')")
    public List<Couche> showCouches(){

        return coucheService.retrieveCouches();
    }

    // http://localhost:8099/api/couche/showcouche/{id_couche}
    @GetMapping("/showcouche/{id_couche}")
    @PreAuthorize("hasAnyAuthority('geologieadmin:read','geologieuser:read','geologieconsult:read')")
    public Couche showOneCouche(@PathVariable("id_couche") Long id_couche){

        return coucheService.retrieveOneCouche(id_couche);
    }

    // http://localhost:8099/api/couche/exportexcelcouche
    @GetMapping("/exportexcelcouche")
    @PreAuthorize("hasAnyAuthority('geologieadmin:read','geologieuser:read','geologieconsult:read')")
    public void generateExcelReport(HttpServletResponse response) throws Exception{

        response.setContentType("application/octet-stream");

        String headerKey = "Content-Disposition";
        String headerValue = "attachment;filename=layers.xls";

        response.setHeader(headerKey, headerValue);

        coucheService.generateExcelCouche(response);

        response.flushBuffer();
    }
}
