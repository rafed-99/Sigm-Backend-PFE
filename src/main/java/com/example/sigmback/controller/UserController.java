package com.example.sigmback.controller;

import com.example.sigmback.model.Point;
import com.example.sigmback.model.User;
import com.example.sigmback.service.PointService;
import com.example.sigmback.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "Requestor-Type", exposedHeaders = "X-Get-Header")
@RequestMapping("/api/admin/user")
@PreAuthorize("hasRole('ADMIN')")
public class UserController {
    @Autowired
    UserService userService;

    // http://localhost:8099/api/point/updatepoint
    @PutMapping("/updateuser")
    @PreAuthorize("hasAuthority('admin:update')")
    public User modifyPoint(@RequestBody User user){

        return userService.updatePoint(user);
    }

    // http://localhost:8099/api/point/deletepoint/{id_point}
    @DeleteMapping("/deleteuser/{id_point}")
    @PreAuthorize("hasAuthority('admin:delete')")
    public void erasePoint(@PathVariable("id_point") Long id_point){

        userService.deletePoint(id_point);
    }

    // http://localhost:8099/api/point/showpoints
    @GetMapping(value = "/showusers")
    @PreAuthorize("hasAuthority('admin:read')")
    public List<User> showPoints(){

        return userService.retrievePoints();
    }

    @GetMapping("/countadmin")
    @PreAuthorize("hasAuthority('admin:read')")
    public Long countAdmin(){

        return userService.countAdmin();
    }

    @GetMapping("/countgeologyadmin")
    @PreAuthorize("hasAuthority('admin:read')")
    public Long countGeologyAdmin(){

        return userService.countGeologyAdmin();
    }

    @GetMapping("/countgeologyuser")
    @PreAuthorize("hasAuthority('admin:read')")
    public Long countGeologyUser(){

        return userService.countGeologyUser();
    }

    @GetMapping("/countgeologyconsult")
    @PreAuthorize("hasAuthority('admin:read')")
    public Long countGeologyConsult(){

        return userService.countGeologyConsult();
    }

    @GetMapping("/countcenteradmin")
    @PreAuthorize("hasAuthority('admin:read')")
    public Long countCenterAdmin(){

        return userService.countCenterAdmin();
    }

    @GetMapping("/countcenteruser")
    @PreAuthorize("hasAuthority('admin:read')")
    public Long countCenterUser(){

        return userService.countCenterUser();
    }

    @GetMapping("/countcenterconfirm")
    @PreAuthorize("hasAuthority('admin:read')")
    public Long countCenterConfirm(){

        return userService.countCenterConfirm();
    }
}
