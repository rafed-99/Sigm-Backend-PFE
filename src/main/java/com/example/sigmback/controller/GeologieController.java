package com.example.sigmback.controller;

import com.example.sigmback.model.Echantillon;
import com.example.sigmback.model.Geologie;
import com.example.sigmback.service.GeologieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/geologie")
public class GeologieController {

    @Autowired
    GeologieService geologieService;

    Float dF;
    Float dTo;

    // http://localhost:8099/api/geologie/addgeologie
    @PostMapping("/addgeologie")
    public Geologie saveGeologie(@RequestBody Geologie geologie){

        return geologieService.addGeologie(geologie);
    }

    // http://localhost:8099/api/geologie/updategeologie
    @PutMapping("/updategeologie")
    public Geologie modifyGeologie(@RequestBody Geologie geologie){

        return geologieService.updateGeologie(geologie);
    }

    // http://localhost:8099/api/geologie/deletegeologie/{id_geologie}
    @DeleteMapping("/deletegeologie/{id_geologie}")
    public void eraseGeologie(@PathVariable("id_geologie") Long id_geologie){

        geologieService.deleteGeologie(id_geologie);
    }

    // http://localhost:8099/api/geologie/showgeologies
    @GetMapping("/showgeologies")
    public List<Geologie> showGeologies(){

        return geologieService.retrieveGeologies();
    }

    // http://localhost:8099/api/geologie/updategeologie/{id_geologie}
    @GetMapping("/showgeologie/{id_geologie}")
    public Geologie showGeologie(@PathVariable("id_geologie") Long id_geologie){

        return geologieService.retrieveOneGeologie(id_geologie);
    }

    // http://localhost:8099/api/geologie/showechantillonsbygeologie/{id_geologie}
    @GetMapping("/showechantillonsbygeologie/{id_geologie}")
    public List<Echantillon> showEchantillonsByGeologie(@PathVariable("id_geologie") Long id_geologie){
        return geologieService.retrieveEchantillonsByGeologie(id_geologie);
    }

}
