package com.felipebaamonde.workshopmongo.services;

import com.felipebaamonde.workshopmongo.dto.UserDto;
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

    public User insert(User obj){
        return userRepository.save(obj);
    }

    public void delete(String id){
        findById(id);
        userRepository.deleteById(id);
    }

    public User update(User obj){
        User newUser = findById(obj.getId());
        updateData(newUser, obj);
        return userRepository.save(newUser);
    }

    private void updateData(User newUser, User obj) {
        newUser.setName(obj.getName());
        newUser.setEmail(obj.getEmail());
    }

    public User fromDTO(UserDto userDto){
        return new User(userDto.getId(), userDto.getName(), userDto.getEmail());
    }
}
