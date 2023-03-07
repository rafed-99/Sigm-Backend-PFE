package com.example.sigmback.controller;

import com.example.sigmback.model.Couche;
import com.example.sigmback.service.CoucheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/couche")
public class CoucheController {

    @Autowired
    CoucheService coucheService;

    // http://localhost:8099/api/couche/addcouche
    @PostMapping("/addcouche")
    public Couche saveCouche(@RequestBody Couche couche){

        return coucheService.addCouche(couche);
    }

    // http://localhost:8099/api/couche/updatecouche
    @PutMapping("/updatecouche")
    public Couche modifyGisement(@RequestBody Couche couche){

        return coucheService.updateCouche(couche);
    }

    // http://localhost:8099/api/couche/deletecouche/{id_couche}
    @DeleteMapping("/deletecouche/{id_couche}")
    public void eraseGisement(@PathVariable("id_couche") Long id_couche){

        coucheService.deleteCouche(id_couche);
    }

    // http://localhost:8099/api/couche/showcouches
    @GetMapping("/showcouche")
    public List<Couche> showCouches(){

        return coucheService.retrieveCouches();
    }

    // http://localhost:8099/api/couche/showcouche/{id_couche}
    @GetMapping("/showcouche/{id_couche}")
    public Couche showOneCouche(@PathVariable("id_couche") Long id_couche){

        return coucheService.retrieveOneCouche(id_couche);
    }
}
