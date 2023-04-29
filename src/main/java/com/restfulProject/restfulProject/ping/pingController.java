package com.restfulProject.restfulProject.ping;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Locale;

@RestController
public class pingController {

    private static int count = 0;
    private MessageSource messageSource;

    public pingController(MessageSource messageSource) {
        this.messageSource = messageSource;
    }


    @RequestMapping(method = RequestMethod.GET, path = "/ping/{name}")
    public String ping(@PathVariable String name){
        count+=1;
        return String.format("Hello %s, ping: %d",name,count);

    }

    @RequestMapping(method = RequestMethod.GET, path = "/pingInter/{name}")
    public String pingMultiLanguage(@PathVariable String name){
        count+=1;
        Locale locale = LocaleContextHolder.getLocale();
        return messageSource.getMessage("good.morning.message",null,"Hii",locale);

        //return String.format("Hello %s, ping: %d",name,count);

    }

}

