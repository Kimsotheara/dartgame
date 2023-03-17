package com.dartgame.dartboardgame.service.implement;

import com.dartgame.dartboardgame.DTO.CreateGameDTO;
import com.dartgame.dartboardgame.DTO.ResponseDTO;
import com.dartgame.dartboardgame.constant.Status;
import com.dartgame.dartboardgame.entity.Game;
import com.dartgame.dartboardgame.entity.GameDetail;
import com.dartgame.dartboardgame.exception.RequestException;
import com.dartgame.dartboardgame.repository.GameDetailRepo;
import com.dartgame.dartboardgame.repository.GameRepo;
import com.dartgame.dartboardgame.service.GameService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class GameServiceImp implements GameService {

    @Autowired
    private GameRepo gameRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private GameDetailRepo gameDetailRepo;

    private static final String FIND_SUCCESS = "Find Successfully...!";
    private static final String CREATE = "Create Successfully...!";
    private static final String NOT_FOUND = "Not Found...!";

    @Override
    public ResponseDTO findAll() throws RequestException {
        try{
            Iterable<Game> game = this.gameRepo.findAll();
            return new ResponseDTO(FIND_SUCCESS,game);
        }catch (Exception e){
            return new ResponseDTO(NOT_FOUND, Status.EXIST.value(), 404);
        }
    }

    @Override
    public ResponseDTO create(CreateGameDTO createGameDTO) throws RequestException{
        Game game = modelMapper.map(createGameDTO,Game.class);
        if (game.getGameDetails() != null){
            for(GameDetail detailObj : createGameDTO.getGameDetails()){
                detailObj.setGame(game);
            }
        }
        this.gameRepo.save(game);
        return new ResponseDTO(CREATE,game);
    }

    @Override
    public ResponseDTO delete(int id) throws RequestException{
        return null;
    }


}
