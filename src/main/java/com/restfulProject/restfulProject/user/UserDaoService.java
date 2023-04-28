package com.restfulProject.restfulProject.user;

import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

@Component
public class UserDaoService {


    private static List<User> users = new ArrayList<>();
    private static int usersCount = 1;
    static {
        users.add(new User(1,"Eren", LocalDate.now().minusYears(30)));
    }

    public List<User> getAllUsers(){
        return users;
    }

    public User finduser(int id){

        Predicate<? super User> predicate = user -> user.getId().equals(id);
        User user = users.stream().filter(predicate).findFirst().orElse(null);

        return user;
    }
    public User saveUser(User user){
        user.setId(++usersCount);
        users.add(user);
        return user;
    }

    public void deleteById (int id){

        Predicate<? super User> predicate = user -> user.getId().equals(id);
        users.removeIf(predicate);
    }



}
