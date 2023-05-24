package com.example.sigmback.controller;

import com.example.sigmback.model.Archive;
import com.example.sigmback.service.ArchiveService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
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

    // http://localhost:8099/api/archive/exportexcelarchivepoint
    @GetMapping("/exportexcelarchivepoint")
    public void generateExcelReportArchivePoint(HttpServletResponse response) throws Exception{

        response.setContentType("application/octet-stream");

        String headerKey = "Content-Disposition";
        String headerValue = "attachment;filename=archivePoints.xls";

        response.setHeader(headerKey, headerValue);

        archiveService.generateExcelArchivePoint(response);

        response.flushBuffer();
    }

    // http://localhost:8099/api/archive/exportexcelarchivebordereau
    @GetMapping("/exportexcelarchivebordereau")
    public void generateExcelReportArchiveBordereau(HttpServletResponse response) throws Exception{

        response.setContentType("application/octet-stream");

        String headerKey = "Content-Disposition";
        String headerValue = "attachment;filename=archiveReceipts.xls";

        response.setHeader(headerKey, headerValue);

        archiveService.generateExcelArchiveBordereau(response);

        response.flushBuffer();
    }
}
