package com.example.sigmback.controller;

import com.example.sigmback.model.Element;
import com.example.sigmback.service.ElementService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/centre/element")
@PreAuthorize("hasAnyRole('CENTRE_ADMIN','CENTRE_USER','CENTRE_CONFIRM','GEOLOGIE_ADMIN','GEOLOGIE_USER','GEOLOGIE_CONSULT')")
public class ElementController {

    @Autowired
    ElementService elementService;

    // http://localhost:8099/api/element/addelement
    @PostMapping("/addelement")
    @PreAuthorize("hasAnyAuthority('centreadmin:create','centreuser:create','centreconfirm:create')")
    public Element saveElement(@RequestBody Element element){

        return elementService.addElement(element);
    }

    // http://localhost:8099/api/element/updateelement
    @PutMapping("/updateelement")
    @PreAuthorize("hasAnyAuthority('centreadmin:update','centreuser:update','centreconfirm:update')")
    public Element modifyElement(@RequestBody Element element){

        return elementService.updateElement(element);
    }

    // http://localhost:8099/api/element/deleteelement/{id_element}
    @DeleteMapping("/deleteelement/{id_element}")
    @PreAuthorize("hasAnyAuthority('centreadmin:delete','centreuser:delete','centreconfirm:delete')")
    public void eraseElement(@PathVariable("id_element") Long id_element){

        elementService.deleteElement(id_element);
    }

    // http://localhost:8099/api/element/showelements
    @GetMapping("/showelements")
    @PreAuthorize("hasAnyAuthority('centreadmin:read','centreuser:read','centreconfirm:read','geologieadmin:read','geologieuser:read','geologieconsult:read')")
    public List<Element> showElement(){

        return elementService.retrieveElements();
    }

    // http://localhost:8099/api/element/showelement/{id_element}
    @GetMapping("/showelement/{id_element}")
    @PreAuthorize("hasAnyAuthority('centreadmin:read','centreuser:read','centreconfirm:read','geologieadmin:read','geologieuser:read','geologieconsult:read')")
    public Element showOneElement(@PathVariable("id_element") Long id_element){

        return elementService.retrieveOneElement(id_element);
    }

    // http://localhost:8099/api/element/exportexcelelement
    @GetMapping("/exportexcelelement")
    @PreAuthorize("hasAnyAuthority('centreadmin:read','centreuser:read','centreconfirm:read')")
    public void generateExcelReport(HttpServletResponse response) throws Exception{

        response.setContentType("application/octet-stream");

        String headerKey = "Content-Disposition";
        String headerValue = "attachment;filename=elements.xls";

        response.setHeader(headerKey, headerValue);

        elementService.generateExcelElement(response);

        response.flushBuffer();
    }
}
