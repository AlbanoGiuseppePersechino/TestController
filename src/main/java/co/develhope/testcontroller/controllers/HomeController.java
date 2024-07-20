package co.develhope.testcontroller.controllers;

import co.develhope.testcontroller.entities.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping("/")
    public @ResponseBody String helloWorld(){
        return "Hello World!";
    }

    @GetMapping("/users")
    public @ResponseBody User getUser(){
        return new User("Albano", "Persechino");
    }
}
