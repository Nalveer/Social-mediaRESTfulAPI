package com.restfulProject.restfulProject.jpa;

import com.restfulProject.restfulProject.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Integer> {



}
