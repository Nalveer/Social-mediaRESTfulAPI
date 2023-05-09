package com.restfulProject.restfulProject.user;

import com.restfulProject.restfulProject.jpa.PostRepository;
import com.restfulProject.restfulProject.jpa.UserRepository;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
public class UserJPAController {

    private UserRepository repository;
    private PostRepository postRepository;
    public UserJPAController(UserRepository repository, PostRepository postRepository) {
        this.repository = repository;
        this.postRepository = postRepository;
    }
    @GetMapping("/jpa/users")
    public List<User> getAllUsers(){

        return repository.findAll();
    }

    @GetMapping("/jpa/users/{id}")
    public Optional<User> getUser(@PathVariable int id){
        Optional<User> user = repository.findById(id);
        if(user.isEmpty()){
            throw new UserNotFoundException("id:"+id);
        }else{
            return user;
        }
    }

    @PostMapping("/jpa/users")
    public ResponseEntity<User> createUser(@Valid @RequestBody User user){
        User saved_user = repository.save(user);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(saved_user.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }

    @DeleteMapping("/jpa/users/{id}")
    public void deleteUser(@PathVariable int id){
        repository.deleteById(id);

    }

    @GetMapping("/jpa/users/{id}/posts")
    public List<Post> retrievePostsForUser(@PathVariable int id){

        Optional<User> user = repository.findById(id);
        if(user.isEmpty()){
            throw new UserNotFoundException("id:"+id);
        }else{
            return user.get().getPosts();
        }

    }

    @PostMapping("/jpa/users/{id}/posts")
    public ResponseEntity<Object> createPostsForUser(@PathVariable int id, @Valid @RequestBody Post post){

        Optional<User> user = repository.findById(id);
        if(user.isEmpty()){
            throw new UserNotFoundException("id:"+id);
        }else{
            post.setUser(user.get());
            Post savedPost = postRepository.save(post);

            URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(savedPost.getId())
                    .toUri();

            return ResponseEntity.created(location).build();
        }

    }

}
