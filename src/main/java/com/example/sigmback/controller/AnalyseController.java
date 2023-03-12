package com.example.sigmback.controller;

import com.example.sigmback.model.Analyses;
import com.example.sigmback.service.AnalyseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/analyse")
public class AnalyseController {

    @Autowired
    AnalyseService analyseService;

    // http://localhost:8099/api/analyse/addanalyse
    @PostMapping("/addanalyse")
    public Analyses saveArchive(@RequestBody Analyses analyse){

        return analyseService.addAnalyse(analyse);
    }

    // http://localhost:8099/api/analyse/updateanalyse
    @PutMapping("/updateanalyse")
    public Analyses modifyAnalyse(@RequestBody Analyses analyse){

        return analyseService.updateAnalyse(analyse);
    }

    // http://localhost:8099/api/analyse/deleteanalyse/{id_analyse}
    @DeleteMapping("/deleteanalyse/{id_analyse}")
    public void eraseAnalyse(@PathVariable("id_analyse") Long id_analyse){

        analyseService.deleteAnalyse(id_analyse);
    }

    // http://localhost:8099/api/analyse/showanalyses
    @GetMapping("/showanalyses")
    public List<Analyses> showAnalyses(){

        return analyseService.retrieveAnalyses();
    }

    // http://localhost:8099/api/analyse/showanalyse/{id_analyse}
    @GetMapping("/showanalyse/{id_analyse}")
    public Analyses showOneAnalyse(@PathVariable("id_analyse") Long id_analyse){

        return analyseService.retrieveOneAnalyse(id_analyse);
    }

    // http://localhost:8099/api/analyse/showanalysebyechantillon/{id_echantillon}
    @GetMapping("/showanalysebyechantillon/{id_echantillon}")
    public List<Analyses> showAnalyseByEchantillon(@PathVariable("id_echantillon") Long id_echantillon){
        return analyseService.retrieveAnalyseByEchantillon(id_echantillon);
    }

    // http://localhost:8099/api/analyse/showanalysesbyelement/{id_element}
    @GetMapping("/showanalysesbyelement/{id_element}")
    public List<Analyses> showAnalyseByElement(@PathVariable("id_element") Long id_element){
        return analyseService.retrieveAnalyseByElements(id_element);
    }
}
