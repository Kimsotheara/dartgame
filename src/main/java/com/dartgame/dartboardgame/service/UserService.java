package com.dartgame.dartboardgame.service;

import com.dartgame.dartboardgame.DTO.ResponseDTO;
import com.dartgame.dartboardgame.DTO.user.CreateUserDto;
import com.dartgame.dartboardgame.DTO.user.UpdateUserDto;
import org.springframework.stereotype.Service;

@Service
public interface UserService {

    ResponseDTO findAll();

    ResponseDTO findById(int userId);

    ResponseDTO findAvailableUsername(String username);

    ResponseDTO create(CreateUserDto createUserDto);

    ResponseDTO update(UpdateUserDto userUpdateDto);

    ResponseDTO delete(int id);

}
