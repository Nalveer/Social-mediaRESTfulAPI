package com.restfulProject.restfulProject.versioning;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VersioningUserController {

    @GetMapping("/v1/user")
    public UserV1 getFirstVersionOfUser(){
        return new UserV1("Bob Charlie");
    }

    @GetMapping("/v2/user")
    public UserV2 getSecondVersionOfUser(){
        return new UserV2(new Name("Bob","Charlie"));
    }

    @GetMapping(path = "/user", params= "version=1")
    public UserV1 getFirstVersionOfUser_param(){
        return new UserV1("Bob Charlie");
    }

    @GetMapping(path = "/user", params= "version=2")
    public UserV2 getSecondVersionOfUser_param(){
        return new UserV2(new Name("Bob","Charlie"));
    }
}
