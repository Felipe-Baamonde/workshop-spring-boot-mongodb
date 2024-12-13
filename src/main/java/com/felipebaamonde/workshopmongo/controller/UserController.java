package com.felipebaamonde.workshopmongo.controller;

import com.felipebaamonde.workshopmongo.dto.UserDto;
import com.felipebaamonde.workshopmongo.entities.User;
import com.felipebaamonde.workshopmongo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<List<UserDto>> findAll(){
        return ResponseEntity.ok().body(userService.findAll().stream().map(x -> new UserDto(x)).collect(Collectors.toList()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> findById(@PathVariable String id){
        return ResponseEntity.ok().body(new UserDto(userService.findById(id)));
    }

    @PostMapping
    public ResponseEntity<User> insert(@RequestBody UserDto userDto){
        User user = userService.fromDTO(userDto);
        return ResponseEntity.created(null).body(userService.insert(user));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id){
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
