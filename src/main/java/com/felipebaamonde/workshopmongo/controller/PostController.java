package com.felipebaamonde.workshopmongo.controller;

import com.felipebaamonde.workshopmongo.controller.util.URL;
import com.felipebaamonde.workshopmongo.dto.UserDto;
import com.felipebaamonde.workshopmongo.entities.Post;
import com.felipebaamonde.workshopmongo.entities.User;
import com.felipebaamonde.workshopmongo.services.PostService;
import com.felipebaamonde.workshopmongo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/posts")
public class PostController {
    @Autowired
    private PostService postService;

    @GetMapping("/{id}")
    public ResponseEntity<Post> findById(@PathVariable String id){
        return ResponseEntity.ok().body(postService.findById(id));
    }

    @GetMapping("/titlesearch")
    public ResponseEntity<List<Post>> findByTitle(@RequestParam(value = "text", defaultValue = "") String text) {
        text = URL.decodeParam(text);
        return ResponseEntity.ok().body(postService.findByTitle(text));
    }

    @GetMapping("/fullsearch")
    public ResponseEntity<List<Post>> fullSearch(
            @RequestParam(value = "text",defaultValue = "") String text,
            @RequestParam(value = "comeco",defaultValue = "") String comeco,
            @RequestParam(value = "fim",defaultValue = "") String fim
    ){
        Date min = URL.convertDate(comeco, new Date(0L));
        Date max = URL.convertDate(fim, new Date());

        List<Post> list = postService.fullSearch(text,min, max);
        return ResponseEntity.ok().body(list);

    }

}
