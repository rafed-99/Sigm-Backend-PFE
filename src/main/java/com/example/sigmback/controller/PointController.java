package com.example.sigmback.controller;

import com.example.sigmback.model.Point;
import com.example.sigmback.service.PointService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/point")
public class PointController {

    @Autowired
    PointService pointService;

    // http://localhost:8099/api/point/addpoint
    @PostMapping("/addpoint")
    public Point savePoint(@RequestBody Point point){

        return pointService.addPoint(point);
    }

    // http://localhost:8099/api/point/updatepoint
    @PutMapping("/updatepoint")
    public Point modifyPoint(@RequestBody Point point){

        return pointService.updatePoint(point);
    }

    // http://localhost:8099/api/point/deletepoint/{id_point}
    @DeleteMapping("/deletepoint/{id_point}")
    public void erasePoint(@PathVariable("id_point") Long id_point){

        pointService.deletePoint(id_point);
    }

    // http://localhost:8099/api/point/showpoints
    @GetMapping("/showpoints")
    public List<Point> showPoints(){

        return pointService.retrievePoints();
    }

    // http://localhost:8099/api/point/showpoint/{id_point}
    @GetMapping("/showpoint/{id_point}")
    public Point showOnePoint(@PathVariable("id_point") Long id_point){

        return pointService.retrieveOnePoint(id_point);
    }

    // http://localhost:8099/api/point/showpointsbygisement/{id_gisement}
    @GetMapping("/showpointsbygisement/{id_gisement}")
    public List<Point> showPointsByGisement(@PathVariable("id_gisement") Long id_gisement){

        return pointService.retrievePointsByGisement(id_gisement);
    }

    // http://localhost:8099/api/point/showpointbyarchive/{id_archive}
    @GetMapping("/showpointbyarchive/{id_archive}")
    public List<Point> showArchiveByPoint(@PathVariable("id_archive") Long id_archive){

        return pointService.retrieveArchiveByPoint(id_archive);
    }

    // http://localhost:8099/api/point/addaffect/{id_gisement}
    @PostMapping("/addaffect/{id_gisement}")
    public Point addAffect(@RequestBody Point point, @PathVariable(name = "id_gisement")  Long id_gisement){

        return pointService.addwithaffectation(id_gisement,point);
    }

    // http://localhost:8099/api/point/addaffect/{id_gisement}
    @PostMapping("/addaffect/{id_gisement}/{id_archive}")
    public Point addAffect(@PathVariable(name = "id_gisement")  Long id_gisement, @PathVariable(name = "id_archive")  Long id_archive){

        return pointService.addToArchive(id_gisement,id_archive);

    }

    // http://localhost:8099/api/point/exportexcelpoint/{id_gisement}
    @GetMapping("/exportexcelpoint/{id_gisement}")
    public void generateExcelReport(HttpServletResponse response , @PathVariable("id_gisement") Long id_gisement) throws Exception{

        response.setContentType("application/octet-stream");

        String headerKey = "Content-Disposition";
        String headerValue = "attachment;filename=points.xls";

        response.setHeader(headerKey, headerValue);

        pointService.generateExcelPoint(response,id_gisement);

        response.flushBuffer();
    }


}
