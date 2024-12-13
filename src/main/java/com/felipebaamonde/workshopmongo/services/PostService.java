package com.felipebaamonde.workshopmongo.services;

import com.felipebaamonde.workshopmongo.dto.UserDto;
import com.felipebaamonde.workshopmongo.entities.Post;
import com.felipebaamonde.workshopmongo.entities.User;
import com.felipebaamonde.workshopmongo.exceptions.ObjectNotFoundException;
import com.felipebaamonde.workshopmongo.repositories.PostRepository;
import com.felipebaamonde.workshopmongo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {
    @Autowired
    private PostRepository postRepository;

    public Post findById(String id) {
        return postRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Post com id %s não encontrado".formatted(id)));
    }

    public List<Post> findByTitle(String title){
        return postRepository.findByTitleContainingIgnoreCase(title);
    }
}


