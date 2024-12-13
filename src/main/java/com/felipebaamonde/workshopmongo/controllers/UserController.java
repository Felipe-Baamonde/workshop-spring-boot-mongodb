package com.felipebaamonde.workshopmongo.controllers;

import com.felipebaamonde.workshopmongo.entities.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @GetMapping
    public ResponseEntity<List<User>> findAll(){
        User user1 = new User("1", "Maria", "maria@gmail.com");
        User user2 = new User("2", "João", "joao@gmail.com");
        List<User> list = Arrays.asList(user1, user2);
        return ResponseEntity.ok().body(list);
    }
}
