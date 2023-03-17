package com.dartgame.dartboardgame.service.implement;

import com.dartgame.dartboardgame.DTO.CreateGamerUserDTO;
import com.dartgame.dartboardgame.DTO.CreateTestDTO;
import com.dartgame.dartboardgame.DTO.ResponseDTO;
import com.dartgame.dartboardgame.constant.Status;
import com.dartgame.dartboardgame.entity.Game;
import com.dartgame.dartboardgame.entity.GamerUser;
import com.dartgame.dartboardgame.entity.Test;
import com.dartgame.dartboardgame.exception.RequestException;
import com.dartgame.dartboardgame.repository.GamerUserRepo;
import com.dartgame.dartboardgame.repository.TestRepository;
import com.dartgame.dartboardgame.service.GamerUserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GamerUserServiceImp implements GamerUserService {

    @Autowired
    private GamerUserRepo gamerUserRepo;

    @Autowired
    private TestRepository testRepository;

    @Autowired
    private ModelMapper modelMapper;

    private static final String FIND_SUCCESS = "Find Successfully...!";
    private static final String CREATE = "Create Successfully...!";
    private static final String NOT_FOUND = "Not Found...!";
    private static final String DELETE = "DELETE...!";
    private static final String EXSIST= "Already exsist...!";

    @Override
    public ResponseDTO findAll() throws RequestException {
        try{
            Iterable<GamerUser> game = this.gamerUserRepo.findAllByStatus(true);
            return new ResponseDTO(FIND_SUCCESS,game);
        }catch (Exception e){
            return new ResponseDTO(NOT_FOUND, Status.EXIST.value(), 404);
        }
    }

    @Override
    public ResponseDTO createTest(CreateTestDTO createTestDTO){
        Test test = this.modelMapper.map(createTestDTO,Test.class);
        test = this.testRepository.save(test);
        return new ResponseDTO(CREATE, test);
    }

    @Override
    public ResponseDTO create(CreateGamerUserDTO createGamerUserDTO) throws RequestException{
        Optional<GamerUser> existName = this.gamerUserRepo.findByName(createGamerUserDTO.getName());
        if (existName.isEmpty()){
            GamerUser game = modelMapper.map(createGamerUserDTO,GamerUser.class);
            game = this.gamerUserRepo.save(game);
            return new ResponseDTO(CREATE,game);
        }
        return new ResponseDTO(EXSIST, Status.EXIST.value(), 409);

    }

    @Override
    public ResponseDTO delete(int id) throws RequestException{
        GamerUser gamerUser =this.checkGamer(id);
        gamerUser.setStatus(false);
       this.gamerUserRepo.save(gamerUser);
        return new ResponseDTO(DELETE,gamerUser);
    }

    private GamerUser checkGamer(int id) throws RequestException{
        Optional<GamerUser> gm = this.gamerUserRepo.findById(id);
        if (gm.isPresent()){
            return gm.get();
        }
        throw new RequestException(String.format(NOT_FOUND, id));

    }


}
