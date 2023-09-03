package com.example.sigmback.service;

import com.example.sigmback.model.Point;
import com.example.sigmback.model.User;
import com.example.sigmback.repository.IPointRepository;
import com.example.sigmback.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;


    public List<User> retrievePoints() {

        return (List<User>)userRepository.findAll();
    }



    public User updatePoint(User user) {

        return userRepository.save(user);
    }



    public void deletePoint(Long id_point) {
        userRepository.deleteById(id_point);
    }

    public Long countAdmin(){
        return userRepository.countAdminUser();
    }
    public Long countGeologyAdmin(){
        return userRepository.countGeologyAdminUser();
    }
    public Long countGeologyUser(){
        return userRepository.countGeologyUser();
    }
    public Long countGeologyConsult(){
        return userRepository.countGeologyConsultantUser();
    }
    public Long countCenterAdmin(){
        return userRepository.countCenterAdminUser();
    }
    public Long countCenterUser(){
        return userRepository.countCenterUser();
    }
    public Long countCenterConfirm(){
        return userRepository.countCenterConfirmUser();
    }
}
