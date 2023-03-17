package com.dartgame.dartboardgame.controller.rest;

import com.dartgame.dartboardgame.DTO.CreateGamerUserDTO;
import com.dartgame.dartboardgame.DTO.CreateTestDTO;
import com.dartgame.dartboardgame.DTO.ResponseDTO;
import com.dartgame.dartboardgame.service.GamerUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/v1/gamer")
public class GamerUserRestController {

    @Autowired
    private GamerUserService gamerUserService;

    @GetMapping
    public ResponseDTO findAll(){
        return this.gamerUserService.findAll();
    }

    @PostMapping
    public ResponseDTO create(@RequestBody CreateGamerUserDTO createGamerUserDTO){
        return this.gamerUserService.create(createGamerUserDTO);
    }

    @PostMapping("/test")
    public ResponseDTO createTest(@RequestBody CreateTestDTO createTestDTO){
        return this.gamerUserService.createTest(createTestDTO);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseDTO delete(@PathVariable int id){
        return this.gamerUserService.delete(id);
    }
}
