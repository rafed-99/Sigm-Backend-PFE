package com.example.sigmback.controller;

import com.example.sigmback.model.Analyse;
import com.example.sigmback.service.AnalyseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/analyse")
public class AnalyseController {

    @Autowired
    AnalyseService analyseService;

    // http://localhost:8099/api/analyse/addanalyse
    @PostMapping("/addanalyse")
    public Analyse saveArchive(@RequestBody Analyse analyse){

        return analyseService.addAnalyse(analyse);
    }

    // http://localhost:8099/api/analyse/updateanalyse
    @PutMapping("/updateanalyse")
    public Analyse modifyAnalyse(@RequestBody Analyse analyse){

        return analyseService.updateAnalyse(analyse);
    }

    // http://localhost:8099/api/analyse/deleteanalyse/{id_analyse}
    @DeleteMapping("/deleteanalyse/{id_analyse}")
    public void eraseAnalyse(@PathVariable("id_analyse") Long id_analyse){

        analyseService.deleteAnalyse(id_analyse);
    }

    // http://localhost:8099/api/analyse/showanalyses
    @GetMapping("/showanalyses")
    public List<Analyse> showAnalyses(){

        return analyseService.retrieveAnalyses();
    }

    // http://localhost:8099/api/analyse/showanalyse/{id_analyse}
    @GetMapping("/showanalyse/{id_analyse}")
    public Analyse showOneAnalyse(@PathVariable("id_analyse") Long id_analyse){

        return analyseService.retrieveOneAnalyse(id_analyse);
    }

    // http://localhost:8099/api/analyse/showanalysebyechantillon/{id_echantillon}
    @GetMapping("/showanalysebyechantillon/{id_echantillon}")
    public List<Analyse> showAnalyseByEchantillon(@PathVariable("id_echantillon") Long id_echantillon){
        return analyseService.retrieveAnalyseByEchantillon(id_echantillon);
    }

    // http://localhost:8099/api/analyse/showanalysesbyelement/{id_element}
    @GetMapping("/showanalysesbyelement/{id_element}")
    public List<Analyse> showAnalyseByElement(@PathVariable("id_element") Long id_element){
        return analyseService.retrieveAnalyseByElements(id_element);
    }
}
