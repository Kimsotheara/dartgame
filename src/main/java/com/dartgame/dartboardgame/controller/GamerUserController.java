package com.dartgame.dartboardgame.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "gamer")
public class GamerUserController {

    @GetMapping()
    public String listPlayer(){
        return "layouts/gamer/list-gamer";
    }

    @GetMapping("/add")
    public String add(){
        return "layouts/gamer/create-gamer";
    }

}
