package com.example.sigmback.controller;

import com.example.sigmback.model.Archive;
import com.example.sigmback.service.ArchiveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/archive")
public class ArchiveController {

    @Autowired
    ArchiveService archiveService;

    // http://localhost:8099/api/archive/addarchive
    @PostMapping("/addarchive")
    public Archive saveArchive(@RequestBody Archive archive){

        return archiveService.addArchive(archive);
    }

    // http://localhost:8099/api/archive/updatearchive
    @PutMapping("/updatearchive")
    public Archive modifyArchive(@RequestBody Archive archive){

        return archiveService.updateArchive(archive);
    }

    // http://localhost:8099/api/archive/deletearchive/{id_archive}
    @DeleteMapping("/deletearchive/{id_archive}")
    public void eraseArchive(@PathVariable("id_archive") Long id_archive){

        archiveService.deleteArchive(id_archive);
    }

    // http://localhost:8099/api/archive/showarchives
    @GetMapping("/showarchives")
    public List<Archive> showArchive(){

        return archiveService.retrieveArchive();
    }

    // http://localhost:8099/api/archive/showarchive/{id_archive}
    @GetMapping("/showarchive/{id_archive}")
    public Archive showOneArchive(@PathVariable("id_archive") Long id_archive){

        return archiveService.retrieveOneArchive(id_archive);
    }
}
