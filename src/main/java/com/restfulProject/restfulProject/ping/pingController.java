package com.restfulProject.restfulProject.ping;

import org.springframework.web.bind.annotation.*;

@RestController
public class pingController {

    private static int count = 0;

    @RequestMapping(method = RequestMethod.GET, path = "/ping/{name}")
    public String ping(@PathVariable String name){
        count+=1;
        return String.format("Hi %s, ping: %d",name,count);

    }

}

