package com.springapitest.restapi.controllers;

import com.springapitest.restapi.models.User;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class userController {

    @RequestMapping(value = "user/{id}")
    public User getUser(@PathVariable String id){
        User myUser = new User();
        myUser.setId(Integer.parseInt(id));
        myUser.setName("Jhonatan");
        myUser.setLastName("Rivera");
        myUser.setPhone("319 901 4545");
        return myUser;
    }
}
