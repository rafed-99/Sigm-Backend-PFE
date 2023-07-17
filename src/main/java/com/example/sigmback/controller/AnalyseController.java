package com.example.sigmback.controller;

import com.example.sigmback.model.Analyses;
import com.example.sigmback.service.AnalyseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/centre/analyse")
@PreAuthorize("hasAnyRole('CENTRE_ADMIN','CENTRE_USER','CENTRE_CONFIRM')")
public class AnalyseController {

    @Autowired
    AnalyseService analyseService;

    // http://localhost:8099/api/analyse/addanalyse
    @PostMapping("/addanalyse")
    @PreAuthorize("hasAnyAuthority('centreadmin:create','centreuser:create','centreconfirm:create')")
    public Analyses saveArchive(@RequestBody Analyses analyse){

        return analyseService.addAnalyse(analyse);
    }

    // http://localhost:8099/api/analyse/updateanalyse
    @PutMapping("/updateanalyse")
    @PreAuthorize("hasAnyAuthority('centreadmin:update','centreuser:update','centreconfirm:update')")
    public Analyses modifyAnalyse(@RequestBody Analyses analyse){

        return analyseService.updateAnalyse(analyse);
    }

    // http://localhost:8099/api/analyse/deleteanalyse/{id_analyse}
    @DeleteMapping("/deleteanalyse/{id_analyse}")
    @PreAuthorize("hasAnyAuthority('centreadmin:delete','centreuser:delete','centreconfirm:delete')")
    public void eraseAnalyse(@PathVariable("id_analyse") Long id_analyse){

        analyseService.deleteAnalyse(id_analyse);
    }

    // http://localhost:8099/api/analyse/showanalyses
    @GetMapping("/showanalyses")
    @PreAuthorize("hasAnyAuthority('centreadmin:read','centreuser:read','centreconfirm:read')")
    public List<Analyses> showAnalyses(){

        return analyseService.retrieveAnalyses();
    }

    // http://localhost:8099/api/analyse/showanalyse/{id_analyse}
    @GetMapping("/showanalyse/{id_analyse}")
    @PreAuthorize("hasAnyAuthority('centreadmin:read','centreuser:read','centreconfirm:read')")
    public Analyses showOneAnalyse(@PathVariable("id_analyse") Long id_analyse){

        return analyseService.retrieveOneAnalyse(id_analyse);
    }

    // http://localhost:8099/api/analyse/showanalysebyechantillon/{id_echantillon}
    @GetMapping("/showanalysebyechantillon/{id_echantillon}")
    @PreAuthorize("hasAnyAuthority('centreadmin:read','centreuser:read','centreconfirm:read')")
    public List<Analyses> showAnalyseByEchantillon(@PathVariable("id_echantillon") Long id_echantillon){
        return analyseService.retrieveAnalyseByEchantillon(id_echantillon);
    }

    // http://localhost:8099/api/analyse/showanalysesbyelement/{id_element}
    @GetMapping("/showanalysesbyelement/{id_element}")
    @PreAuthorize("hasAnyAuthority('centreadmin:read','centreuser:read','centreconfirm:read')")
    public List<Analyses> showAnalyseByElement(@PathVariable("id_element") Long id_element){
        return analyseService.retrieveAnalyseByElements(id_element);
    }
}
