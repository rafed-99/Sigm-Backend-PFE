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
}
