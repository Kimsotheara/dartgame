package com.dartgame.dartboardgame.service;

import com.dartgame.dartboardgame.DTO.CreateGamerUserDTO;
import com.dartgame.dartboardgame.DTO.CreateTestDTO;
import com.dartgame.dartboardgame.DTO.ResponseDTO;
import com.dartgame.dartboardgame.exception.RequestException;
import org.springframework.stereotype.Service;

@Service
public interface GamerUserService {

    ResponseDTO findAll() throws RequestException;

    ResponseDTO create(CreateGamerUserDTO createGamerUserDTO) throws RequestException;

    ResponseDTO createTest(CreateTestDTO createTestDTO) throws RequestException;

    ResponseDTO delete(int id) throws RequestException;

}
