package com.example.sigmback.controller;

import com.example.sigmback.model.Archive;
import com.example.sigmback.model.Geologie;
import com.example.sigmback.model.Point;
import com.example.sigmback.service.PointService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
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
}
