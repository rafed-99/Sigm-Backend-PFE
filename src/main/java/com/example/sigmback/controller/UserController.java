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
}
