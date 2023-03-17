package com.dartgame.dartboardgame.controller.rest;

import com.dartgame.dartboardgame.DTO.CreateGameDTO;
import com.dartgame.dartboardgame.DTO.ResponseDTO;
import com.dartgame.dartboardgame.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1/game")
public class GameRestController {

    @Autowired
    private GameService gameService;

    @GetMapping
    public ResponseDTO findAll(){
        return this.gameService.findAll();
    }

    @PostMapping
    public ResponseDTO create(CreateGameDTO createGameDTO){
        return this.gameService.create(createGameDTO);
    }
}
