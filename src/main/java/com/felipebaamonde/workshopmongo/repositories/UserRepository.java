package com.felipebaamonde.workshopmongo.repositories;

import com.felipebaamonde.workshopmongo.entities.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<User, String> {

}
