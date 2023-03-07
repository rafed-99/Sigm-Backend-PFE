package com.example.sigmback.controller;

import com.example.sigmback.model.Bordereau;
import com.example.sigmback.service.BordereauService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bordereau")
public class BordereauController {

    @Autowired
    BordereauService bordereauService;

    // http://localhost:8099/api/bordereau/addbordereau
    @PostMapping("/addbordereau")
    public Bordereau saveBordereau(@RequestBody Bordereau bordereau){

        return bordereauService.addBordereau(bordereau);
    }

    // http://localhost:8099/api/bordereau/updatebordereau
    @PutMapping("/updatebordereau")
    public Bordereau modifyBordereau(@RequestBody Bordereau bordereau){

        return bordereauService.updateBordereau(bordereau);
    }

    // http://localhost:8099/api/bordereau/deletebordereau/{id_bordereau}
    @DeleteMapping("/deletebordereau/{id_bordereau}")
    public void eraseBordereau(@PathVariable("id_bordereau") Long id_bordereau){

        bordereauService.deleteBordereau(id_bordereau);
    }

    // http://localhost:8099/api/bordereau/showbordereaux
    @GetMapping("/showbordereaux")
    public List<Bordereau> showBordereaux(){

        return bordereauService.retrieveBordereaux();
    }

    // http://localhost:8099/api/bordereau/showbordereau/{id_bordereau}
    @GetMapping("/showbordereau/{id_bordereau}")
    public Bordereau showOneBordereau(@PathVariable("id_bordereau") Long id_bordereau){

        return bordereauService.retrieveOneBordereau(id_bordereau);
    }
}
