package com.example.sigmback.controller;

import com.example.sigmback.model.Coupure;
import com.example.sigmback.service.CoupureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/coupure")
public class CoupureController {

    @Autowired
    CoupureService coupureService;

    // http://localhost:8099/api/coupure/addcoupure
    @PostMapping("/addcoupure")
    public Coupure saveCoupure(@RequestBody Coupure coupure){

        return coupureService.addCoupure(coupure);
    }

    // http://localhost:8099/api/coupure/updatecoupure
    @PutMapping("/updatecoupure")
    public Coupure modifyCoupure(@RequestBody Coupure coupure){

        return coupureService.updateCoupure(coupure);
    }

    // http://localhost:8099/api/coupure/deletecoupure/{id_coupure}
    @DeleteMapping("/deletecoupure/{id_coupure}")
    public void eraseCoupure(@PathVariable("id_coupure") Long id_coupure){

        coupureService.deleteCoupure(id_coupure);
    }

    // http://localhost:8099/api/coupure/showcoupures
    @GetMapping("/showcoupures")
    public List<Coupure> showCoupures(){

        return coupureService.retrieveCoupures();
    }

    // http://localhost:8099/api/coupure/showcoupure/{id_coupure}
    @GetMapping("/showcoupure/{id_coupure}")
    public Coupure showOneCoupure(@PathVariable("id_coupure") Long id_coupure){

        return coupureService.retrieveOneCoupure(id_coupure);
    }
}
