package com.felipebaamonde.workshopmongo.config;

import com.felipebaamonde.workshopmongo.dto.AuthorDto;
import com.felipebaamonde.workshopmongo.dto.CommentDto;
import com.felipebaamonde.workshopmongo.entities.Post;
import com.felipebaamonde.workshopmongo.entities.User;
import com.felipebaamonde.workshopmongo.repositories.PostRepository;
import com.felipebaamonde.workshopmongo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.TimeZone;

@Configuration
public class Instantiation implements CommandLineRunner {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostRepository postRepository;
    @Override
    public void run(String... args) throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        sdf.setTimeZone(TimeZone.getTimeZone("GMT"));

        userRepository.deleteAll();
        postRepository.deleteAll();

        User maria = new User(null, "Maria Brown", "maria@gmail.com");
        User alex = new User(null, "Alex Green", "alex@gmail.com");
        User bob = new User(null, "Bob Grey", "bob@gmail.com");
        userRepository.saveAll(Arrays.asList(maria, alex, bob));

        Post post1 = new Post(null, sdf.parse("23/12/2024"),"Partiu viagem","Vou viajar para São Paulo. Abraços!", new AuthorDto(maria));
        Post post2 = new Post(null, sdf.parse("26/12/2024"),"Melhor almoço","Acabei de fazer uma feijoada incrível!", new AuthorDto(maria));

        CommentDto c1 = new CommentDto("Boa viagem mano!", sdf.parse("23/12/2024"), new AuthorDto(alex));
        CommentDto c2 = new CommentDto("Aproveite", sdf.parse("24/12/2024"), new AuthorDto(bob));
        CommentDto c3 = new CommentDto("Tenha um ótimo dia!", sdf.parse("25/12/2024"), new AuthorDto(alex));

        post1.getComments().addAll(Arrays.asList(c1,c2));
        post2.getComments().addAll(Arrays.asList(c3));

        postRepository.saveAll(Arrays.asList(post1, post2));

        maria.getPosts().addAll(Arrays.asList(post1,post2));
        userRepository.save(maria);
    }
}
