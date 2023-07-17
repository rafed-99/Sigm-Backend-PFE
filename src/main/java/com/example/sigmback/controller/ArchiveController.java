package com.example.sigmback.controller;

import com.example.sigmback.model.Archive;
import com.example.sigmback.service.ArchiveService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/archive")
@PreAuthorize("hasAnyRole('CENTRE_ADMIN','CENTRE_USER','CENTRE_CONFIRM','GEOLOGIE_ADMIN','GEOLOGIE_USER','GEOLOGIE_CONSULT')")
public class ArchiveController {

    @Autowired
    ArchiveService archiveService;

    // http://localhost:8099/api/archive/addarchive
    @PostMapping("/addarchive")
    @PreAuthorize("hasAnyAuthority('centreadmin:create','centreuser:create','centreconfirm:create','geologieadmin:create','geologieuser:create')")
    public Archive saveArchive(@RequestBody Archive archive){

        return archiveService.addArchive(archive);
    }

    // http://localhost:8099/api/archive/updatearchive
    @PutMapping("/updatearchive")
    @PreAuthorize("hasAnyAuthority('centreadmin:update','centreuser:update','centreconfirm:update','geologieadmin:update','geologieuser:update')")
    public Archive modifyArchive(@RequestBody Archive archive){

        return archiveService.updateArchive(archive);
    }

    // http://localhost:8099/api/archive/deletearchive/{id_archive}
    @DeleteMapping("/deletearchive/{id_archive}")
    @PreAuthorize("hasAnyAuthority('centreadmin:delete','centreuser:delete','centreconfirm:delete','geologieadmin:delete')")
    public void eraseArchive(@PathVariable("id_archive") Long id_archive){

        archiveService.deleteArchive(id_archive);
    }

    // http://localhost:8099/api/archive/showarchives
    @GetMapping("/showarchives")
    @PreAuthorize("hasAnyAuthority('centreadmin:read','centreuser:read','centreconfirm:read','geologieadmin:read','geologieuser:read','geologieconsult:read')")
    public List<Archive> showArchive(){

        return archiveService.retrieveArchive();
    }

    // http://localhost:8099/api/archive/showarchive/{id_archive}
    @GetMapping("/showarchive/{id_archive}")
    @PreAuthorize("hasAnyAuthority('centreadmin:read','centreuser:read','centreconfirm:read','geologieadmin:read','geologieuser:read','geologieconsult:read')")
    public Archive showOneArchive(@PathVariable("id_archive") Long id_archive){

        return archiveService.retrieveOneArchive(id_archive);
    }

    // http://localhost:8099/api/archive/exportexcelarchivepoint
    @GetMapping("/exportexcelarchivepoint")
    @PreAuthorize("hasAnyAuthority('centreadmin:read','centreuser:read','centreconfirm:read','geologieadmin:read','geologieuser:read','geologieconsult:read')")
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
    @PreAuthorize("hasAnyAuthority('centreadmin:read','centreuser:read','centreconfirm:read','geologieadmin:read','geologieuser:read','geologieconsult:read')")
    public void generateExcelReportArchiveBordereau(HttpServletResponse response) throws Exception{

        response.setContentType("application/octet-stream");

        String headerKey = "Content-Disposition";
        String headerValue = "attachment;filename=archiveReceipts.xls";

        response.setHeader(headerKey, headerValue);

        archiveService.generateExcelArchiveBordereau(response);

        response.flushBuffer();
    }
}
