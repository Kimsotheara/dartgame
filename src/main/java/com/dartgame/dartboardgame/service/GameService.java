package com.dartgame.dartboardgame.service;

import com.dartgame.dartboardgame.DTO.CreateGameDTO;
import com.dartgame.dartboardgame.DTO.ResponseDTO;
import org.springframework.stereotype.Service;

@Service
public interface GameService {

    ResponseDTO findAll();

    ResponseDTO create(CreateGameDTO createGameDTO);

    ResponseDTO delete(int id);

}
