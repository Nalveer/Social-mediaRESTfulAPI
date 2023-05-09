package com.restfulProject.restfulProject.jpa;

import com.restfulProject.restfulProject.user.Post;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post,Integer> {
}
