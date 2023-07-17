package com.example.sigmback.controller;

import com.example.sigmback.model.Gisement;
import com.example.sigmback.service.GisementService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/geologie/gisement")
@PreAuthorize("hasAnyRole('GEOLOGIE_ADMIN','GEOLOGIE_USER','GEOLOGIE_CONSULT')")
public class GisementController {

    @Autowired
    GisementService gisementService;

    // http://localhost:8099/api/gisement/addgisement
    @PostMapping("/addgisement")
    @PreAuthorize("hasAnyAuthority('geologieadmin:create','geologieuser:create')")
    public Gisement saveGisement(@RequestBody Gisement gisement){

        return gisementService.addGisement(gisement);
    }

    // http://localhost:8099/api/gisement/updategisement
    @PutMapping("/updategisement")
    @PreAuthorize("hasAnyAuthority('geologieadmin:update','geologieuser:update')")
    public Gisement modifyGisement(@RequestBody Gisement gisement){

        return gisementService.updateGisement(gisement);
    }

    // http://localhost:8099/api/gisement/deletegisement/{id_gisement}
    @DeleteMapping("/deletegisement/{id_gisement}")
    @PreAuthorize("hasAnyAuthority('geologieadmin:delete')")
    public void eraseGisement(@PathVariable("id_gisement") Long id_gisement){

        gisementService.deleteGisement(id_gisement);
    }

    // http://localhost:8099/api/gisement/showgisements
    @GetMapping("/showgisements")
    @PreAuthorize("hasAnyAuthority('geologieadmin:read','geologieuser:read','geologieconsult:read')")
    public List<Gisement> showGisement(){

        return gisementService.retrieveGisement();
    }

    // http://localhost:8099/api/gisement/showgisement/{id_gisement}
    @GetMapping("/showgisement/{id_gisement}")
    @PreAuthorize("hasAnyAuthority('geologieadmin:read','geologieuser:read','geologieconsult:read')")
    public Gisement showOneGisement(@PathVariable("id_gisement") Long id_gisement){

        return gisementService.retrieveOneGisement(id_gisement);
    }

    // http://localhost:8099/api/gisement/showgisementsbysecteur/{secteur}
    @GetMapping("/showgisementsbysecteur/{secteur}")
    @PreAuthorize("hasAnyAuthority('geologieadmin:read','geologieuser:read','geologieconsult:read')")
    public List<Gisement> showGisementBySecteur(@PathVariable("secteur") String secteur){

        return gisementService.findGisementsBySecteur(secteur);
    }

    // http://localhost:8099/api/gisement/countgisementsbysecteur/{secteur}
    @GetMapping("/countgisementsbysecteur/{secteur}")
    @PreAuthorize("hasAnyAuthority('geologieadmin:read','geologieuser:read','geologieconsult:read')")
    public Long countGisementsBySecteur(@PathVariable("secteur") String secteur){

        return gisementService.countGisementBySecteur(secteur);
    }
/*
    // http://localhost:8099/api/gisement/ordergisementsbysecteurasc
    @GetMapping("/ordergisementsbysecteurasc")
    public List<Gisement> orderGisementsBySecteurAsc(){

        return gisementService.orderGisementBySecteurAsc();
    }

    // http://localhost:8099/api/gisement/ordergisementsbysecteurdesc
    @GetMapping("/ordergisementsbysecteurdesc")
    public List<Gisement> orderGisementsBySecteurDesc(){

        return gisementService.orderGisementBySecteurDesc();
    }*/

    // http://localhost:8099/api/gisement/searchgisementsbylibelle
    /*@GetMapping("/searchgisementsbylibelle")
    public List<Gisement> searchGisementByLibelle(@Param("libelle") String libelle){

        return gisementService.searchByLibelle(libelle);
    }*/

    // http://localhost:8099/api/gisement/exportexcelgisements
    @GetMapping("/exportexcelgisements")
    @PreAuthorize("hasAnyAuthority('geologieadmin:read','geologieuser:read','geologieconsult:read')")
    public void generateExcelReport(HttpServletResponse response) throws Exception{

        response.setContentType("application/octet-stream");

        String headerKey = "Content-Disposition";
        String headerValue = "attachment;filename=gisements.xls";

        response.setHeader(headerKey, headerValue);

        gisementService.generateExcelGisement(response);

        response.flushBuffer();
    }
}
