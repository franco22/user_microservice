package com.example.usermicroservice.controller;

import org.apache.catalina.User;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.usermicroservice.entity.UserEntity;
import com.example.usermicroservice.service.ServiceUser;

import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final ServiceUser serviceUser;
/*
    @PostMapping("/api/users/login")
    public UserEntity login(@RequestParam("user") String username, @RequestParam("password") String pwd) {

        UUID token = UUID.randomUUID();

        serviceUser.getUser()


        return user;

    }*/

    @PostMapping
    public ResponseEntity<?> saveUser(@RequestBody UserEntity user){

        UserEntity userBD = serviceUser.getUser(user.getEmail());

        if(userBD != null){
            Map<String, Object> map = new LinkedHashMap<>();
            map.put("message", "El correo ya se encuentra registrado");
            return new ResponseEntity<> (map, HttpStatus.NOT_ACCEPTABLE);
        }

        LocalDateTime today = LocalDateTime.now();
        UUID token = UUID.randomUUID();
        user.setCreated(today);
        user.setIsactive(Boolean.TRUE);
        user.setToken(token);
        user.setModified(today);
        user.setLast_login(today);

        return new ResponseEntity<UserEntity>( serviceUser.saveUser(user), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<?> getInfoUser(@RequestBody UserEntity user){

        UserEntity userBD = serviceUser.getUser(user.getEmail());

        if(userBD == null){
            Map<String, Object> map = new LinkedHashMap<>();
            map.put("message", "El usuario no se encuentra registrado");
            return new ResponseEntity<> (map, HttpStatus.NOT_ACCEPTABLE);
        }

        Map<String, Object> map = new LinkedHashMap<>();

        map.put("created", userBD.getCreated());
        map.put("modified", userBD.getModified());
        map.put("last_login", userBD.getLast_login());
        map.put("token", userBD.getToken().toString());
        map.put("isactive", userBD.getIsactive().toString());

        return new ResponseEntity<> (map, HttpStatus.CREATED);
    }
    @GetMapping("/api/users/{id}")
    public ResponseEntity<UserEntity> getUser(@PathVariable UUID id) {
        try{
            return new ResponseEntity<UserEntity>(serviceUser.getUserById(id), HttpStatus.OK);
        }catch (Exception exception){
            return new ResponseEntity<UserEntity>(HttpStatus.NOT_FOUND);
        }

    }
    @PutMapping("/api/users/{id}")
    public ResponseEntity<?> modifyUser(@RequestBody UserEntity user, @PathVariable UUID id){
        try{
            LocalDateTime today = LocalDateTime.now();

            UserEntity userExisting = serviceUser.getUserById(id);
            userExisting.setName(user.getName());
            userExisting.setPassword(user.getPassword());
            userExisting.setModified(today);

            return new ResponseEntity<UserEntity>(serviceUser.saveUser(userExisting), HttpStatus.OK);
        }catch (Exception exception){
            return new ResponseEntity<UserEntity>(HttpStatus.NOT_FOUND);
        }
    }

}
