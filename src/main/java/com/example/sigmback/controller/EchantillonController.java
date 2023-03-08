package com.example.sigmback.controller;

import com.example.sigmback.model.Echantillon;
import com.example.sigmback.service.EchantillonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/echantillon")
public class EchantillonController {

    @Autowired
    EchantillonService echantillonService;

    // http://localhost:8099/api/echantillon/addechantillon
    @PostMapping("/addechantillon")
    public Echantillon saveEchantillon(@RequestBody Echantillon echantillon){

        return echantillonService.addEchantillon(echantillon);
    }

    // http://localhost:8099/api/echantillon/updateechantillon
    @PutMapping("/updateechantillon")
    public Echantillon modifyEchantillon(@RequestBody Echantillon echantillon){

        return echantillonService.updateEchantillon(echantillon);
    }

    // http://localhost:8099/api/echantillon/deleteechantillon/{id_echantillon}
    @DeleteMapping("/deleteechantillon/{id_echantillon}")
    public void eraseEchantillon(@PathVariable("id_echantillon") Long id_echantillon){

        echantillonService.deleteEchantillon(id_echantillon);
    }

    // http://localhost:8099/api/echantillon/showechantillons
    @GetMapping("/showechantillons")
    public List<Echantillon> showEchantillons(){

        return echantillonService.retrieveEchantillons();
    }

    // http://localhost:8099/api/echantillon/showechantillon/{id_echantillon}
    @GetMapping("/showechantillon/{id_echantillon}")
    public Echantillon showOneEchantillon(@PathVariable("id_echantillon") Long id_echantillon){

        return echantillonService.retrieveOneEchantillon(id_echantillon);
    }

    // http://localhost:8099/api/echantillon/showechantillonsbypoint/{id_point}
    @GetMapping("/showechantillonsbypoint/{id_point}")
    public List<Echantillon> show(@PathVariable("id_point") Long id_point){
        return echantillonService.retrieveEchantillonByPoint(id_point);
    }

    // http://localhost:8099/api/echantillon/showechantillonsbygeologie/{id_geologie}
    @GetMapping("/showechantillonsbygeologie/{id_geologie}")
    public List<Echantillon> showEchantillonsByGeologie(@PathVariable("id_geologie") Long id_geologie){
        return echantillonService.retrieveEchantillonsByGeologie(id_geologie);
    }

    // http://localhost:8099/api/echantillon/showechantillonbybordereau/{id_bordereau}
    @GetMapping("/showechantillonbybordereau/{id_bordereau}")
    public List<Echantillon> showEchantillonByBordereau(@PathVariable("id_bordereau") Long id_bordereau){

        return echantillonService.retrieveEchantillonByBordereau(id_bordereau);
    }
}
