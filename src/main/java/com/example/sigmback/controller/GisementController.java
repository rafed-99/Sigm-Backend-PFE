package com.example.sigmback.controller;

import com.example.sigmback.model.Gisement;
import com.example.sigmback.service.GisementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/gisement")
public class GisementController {

    @Autowired
    GisementService gisementService;

    // http://localhost:8099/api/gisement/addgisement
    @PostMapping("/addgisement")
    public Gisement saveGisement(@RequestBody Gisement gisement){

        return gisementService.addGisement(gisement);
    }

    // http://localhost:8099/api/gisement/updategisement
    @PutMapping("/updategisement")
    public Gisement modifyGisement(@RequestBody Gisement gisement){

        return gisementService.updateGisement(gisement);
    }

    // http://localhost:8099/api/gisement/deletegisement/{id_gisement}
    @DeleteMapping("/deletegisement/{id_gisement}")
    public void eraseGisement(@PathVariable("id_gisement") Long id_gisement){

        gisementService.deleteGisement(id_gisement);
    }

    // http://localhost:8099/api/gisement/showgisements
    @GetMapping("/showgisements")
    public List<Gisement> showGisement(){

        return gisementService.retrieveGisement();
    }

    // http://localhost:8099/api/gisement/showgisements/{id_gisement}
    @GetMapping("/showgisement/{id_gisement}")
    public Gisement showOneGisement(@PathVariable("id_gisement") Long id_gisement){

        return gisementService.retrieveOneGisement(id_gisement);
    }

    // http://localhost:8099/api/gisement/showgisementsbysecteur/{secteur}
    @GetMapping("/showgisementsbysecteur/{secteur}")
    public List<Gisement> showGisementBySecteur(@PathVariable("secteur") String secteur){

        return gisementService.findGisementsBySecteur(secteur);
    }

    // http://localhost:8099/api/gisement/countgisementsbysecteur/{secteur}
    @GetMapping("/countgisementsbysecteur/{secteur}")
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
}
