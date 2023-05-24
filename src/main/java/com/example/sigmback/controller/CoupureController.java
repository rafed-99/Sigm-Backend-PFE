package com.example.sigmback.controller;

import com.example.sigmback.model.Coupure;
import com.example.sigmback.service.CoupureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/coupure")
public class CoupureController {

    @Autowired
    CoupureService coupureService;

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
