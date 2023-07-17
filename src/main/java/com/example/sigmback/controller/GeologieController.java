package com.example.sigmback.controller;

import com.example.sigmback.model.Geologie;
import com.example.sigmback.service.GeologieService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin()
@RequestMapping("/api/geologie/geo")
@PreAuthorize("hasAnyRole('GEOLOGIE_ADMIN','GEOLOGIE_USER','GEOLOGIE_CONSULT')")
public class GeologieController {

    @Autowired
    GeologieService geologieService;


    // http://localhost:8099/api/geologie/addgeologie
    @PostMapping("/addgeologie")
    @PreAuthorize("hasAnyAuthority('geologieadmin:create','geologieuser:create')")
    public Geologie saveGeologie(@RequestBody Geologie geologie){

        return geologieService.addGeologie(geologie);
    }

    // http://localhost:8099/api/geologie/updategeologie
    @PutMapping("/updategeologie")
    @PreAuthorize("hasAnyAuthority('geologieadmin:update','geologieuser:update')")
    public Geologie modifyGeologie(@RequestBody Geologie geologie){

        return geologieService.updateGeologie(geologie);
    }

    // http://localhost:8099/api/geologie/deletegeologie/{id_geologie}
    @DeleteMapping("/deletegeologie/{id_geologie}")
    @PreAuthorize("hasAnyAuthority('geologieadmin:delete')")
    public void eraseGeologie(@PathVariable("id_geologie") Long id_geologie){

        geologieService.deleteGeologie(id_geologie);
    }

    // http://localhost:8099/api/geologie/showgeologies
    @GetMapping("/showgeologies")
    @PreAuthorize("hasAnyAuthority('geologieadmin:read','geologieuser:read','geologieconsult:read')")
    public List<Geologie> showGeologies(){

        return geologieService.retrieveGeologies();
    }

    // http://localhost:8099/api/geologie/updategeologie/{id_geologie}
    @GetMapping("/showgeologie/{id_geologie}")
    @PreAuthorize("hasAnyAuthority('geologieadmin:read','geologieuser:read','geologieconsult:read')")
    public Geologie showGeologie(@PathVariable("id_geologie") Long id_geologie){

        return geologieService.retrieveOneGeologie(id_geologie);
    }

    // http://localhost:8099/api/geologie/showgeologiesbypoint/{id_point}
    @GetMapping("/showgeologiesbypoint/{id_point}")
    @PreAuthorize("hasAnyAuthority('geologieadmin:read','geologieuser:read','geologieconsult:read')")
    public List<Geologie> showGeologiesByPoints(@PathVariable("id_point") Long id_point){
        return geologieService.retrieveGeologieByPoint(id_point);
    }

    // http://localhost:8099/api/geologie/addaffect/{id_point}/{id_couche}
    @PostMapping("/addaffect/{id_point}/{id_couche}")
    @PreAuthorize("hasAnyAuthority('geologieadmin:create','geologieuser:create')")
    public Geologie addAffect(@RequestBody Geologie geologie, @PathVariable(name = "id_point")  Long id_point, @PathVariable(name = "id_couche")  Long id_couche){

        return geologieService.addwithaffectation(id_point,id_couche,geologie);
    }

    // http://localhost:8099/api/geologie/exportexcelgeologie/{pointId}
    @GetMapping("/exportexcelgeologie/{pointId}")
    @PreAuthorize("hasAnyAuthority('geologieadmin:read','geologieuser:read','geologieconsult:read')")
    public void generateExcelReport(HttpServletResponse response, @PathVariable("pointId") Long pointId) throws Exception{

        response.setContentType("application/octet-stream");

        String headerKey = "Content-Disposition";
        String headerValue = "attachment;filename=geologies.xls";

        response.setHeader(headerKey, headerValue);

        geologieService.generateExcelGeologie(response,pointId);

        response.flushBuffer();
    }
}
