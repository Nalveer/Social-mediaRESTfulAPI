package com.restfulProject.restfulProject.user;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
public class UserController {

    private UserDaoService dao_service;

    public UserController(UserDaoService dao_service) {
        this.dao_service = dao_service;
    }
    @GetMapping("/users")
    public List<User> getAllUsers(){
        return dao_service.getAllUsers();
    }

    @GetMapping("/users/{id}")
    public User getUser(@PathVariable int id){
        User user = dao_service.finduser(id);
        if(user==null){
            throw new UserNotFoundException("id:"+id);
        }else{
            return user;
        }
    }

    @PostMapping("/users")
    public ResponseEntity<User> createUser(@Valid @RequestBody User user){
        User saved_user = dao_service.saveUser(user);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(saved_user.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }

    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable int id){
        dao_service.deleteById(id);

    }

}
