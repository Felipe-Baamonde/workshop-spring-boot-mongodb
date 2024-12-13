package com.felipebaamonde.workshopmongo.services;

import com.felipebaamonde.workshopmongo.entities.User;
import com.felipebaamonde.workshopmongo.exceptions.ObjectNotFoundException;
import com.felipebaamonde.workshopmongo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    public List<User> findAll(){
        return userRepository.findAll();
    }

    public User findById(String id){
        return userRepository.findById(id).orElseThrow( () -> new ObjectNotFoundException("Usuário com id %s não encontrado".formatted(id)));
    }
}
