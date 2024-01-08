package com.abishek.app.rest.controller;

import com.abishek.app.rest.Repo.UserRepo;
import com.abishek.app.rest.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ApiControllers {
    @Autowired
    private UserRepo userRepo;
    @GetMapping(value = "/")
    public String getPage(){

        return "Welcome to demonstration of the simple CRUD API that I have created. ";
    }
    @GetMapping(value = "/users")
    public List<User> getUsers(){
        return userRepo.findAll();

    }
    @PostMapping(value = "/save")
    public String saveUser(@RequestBody User user){
        userRepo.save(user);
        return "saved...";
    }
    @PutMapping(value = "/update/{id}")
    public String updateUser(@PathVariable long id, @RequestBody User user){
        User updatedUser = userRepo.findById(id).get();
        updatedUser.setFirstname(user.getFirstname());
        updatedUser.setLastname(user.getLastname());
        updatedUser.setOccupation(user.getOccupation());
        updatedUser.setAge(user.getAge());
        userRepo.save(updatedUser);
        return"updated the values...";
    }
    @DeleteMapping(value = "/delete/{id}")
    public String deleteUser(@PathVariable long id){
        User deleteUser=userRepo.findById(id).get();
        userRepo.delete(deleteUser);
        return("Deleted the user with id:"+id);
    }

}
