package com.dartgame.dartboardgame.service.implement;

import com.dartgame.dartboardgame.DTO.ResponseDTO;
import com.dartgame.dartboardgame.DTO.user.CreateUserDto;
import com.dartgame.dartboardgame.DTO.user.UpdateUserDto;
import com.dartgame.dartboardgame.constant.Status;
import com.dartgame.dartboardgame.entity.User;
import com.dartgame.dartboardgame.exception.RequestException;
import com.dartgame.dartboardgame.repository.UserRepository;
import com.dartgame.dartboardgame.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.stream.StreamSupport;

@Service
public class UserServiceImpl implements UserService {
    private static final String FIND_SUCCESS = "User find successfully";
    private static final String CREATE_SUCCESS = "User create successfully";
    private static final String UPDATE_SUCCESS = "User update successfully";
    private static final String DELETE_SUCCESS = "User delete successfully";
    private static final String NOT_FOUND = "User with id %s not found!";
    private static final String NOT_AVAILABLE = "Username not available!";
    private static final String DEFAULT_ADMIN_NOT_ALLOW_DELETE = "Default admin user not allow to delete!";


    private UserRepository userRepository;
    private ModelMapper modelMapper;
    private BCryptPasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public ResponseDTO findAll() {
        Iterable<User> userList = this.userRepository.findAllByStatus(true);
        return new ResponseDTO(FIND_SUCCESS, userList);
    }

    @Override
    public ResponseDTO findById(int userId) throws RequestException {
        User user = this.checkExistingUser(userId);
        return new ResponseDTO(FIND_SUCCESS, user);
    }

    @Override
    public ResponseDTO findAvailableUsername(String username) {
        ResponseDTO responseDto = new ResponseDTO();
        User user = this.userRepository.findByUsername(username);
        if (user != null) {
            Iterable<User> users = this.userRepository.findByUsernameStartsWith(username);
            int existingNameCount = ((Collection<?>) users).size();
            username = this.findAvailableName(users, username, existingNameCount + 1);
        }
        responseDto.setData(username);
        return responseDto;
    }

    @Override
    public ResponseDTO create(CreateUserDto createUserDto) throws RequestException {
        // Validate username
        User user = this.userRepository.findByUsername(createUserDto.getUsername());
        if (user != null) {
            throw new RequestException(NOT_AVAILABLE);
        }
        // Encode Password
        createUserDto.setPassword(passwordEncoder.encode(createUserDto.getPassword()));

        // Convert UserDto to entity User
        User newUser = modelMapper.map(createUserDto, User.class);

        // Save new user
        user = this.userRepository.save(newUser);
        return new ResponseDTO(CREATE_SUCCESS, user);
    }

    @Override
    public ResponseDTO update(UpdateUserDto userUpdateDto) throws RequestException {

        User existingUser = this.checkExistingUser(userUpdateDto.getId());
        User updateUser = this.modelMapper.map(userUpdateDto,User.class);

        //if oldPassword and new password are matched, user does not change the password
        if(!userUpdateDto.getPassword().equals(existingUser.getPassword())){
            updateUser.setPassword(passwordEncoder.encode(userUpdateDto.getPassword()));
        }

        try {
            updateUser = this.userRepository.save(updateUser);
            return new ResponseDTO(UPDATE_SUCCESS, updateUser);

        }catch (Exception e){
            return new ResponseDTO("User already exists", Status.EXIST.value(), 409);
        }
    }

    @Override
    public ResponseDTO delete(int id) throws RequestException {
        User user = this.checkExistingUser(id);
        if ("admin".equals(user.getUsername())) {
            throw new RequestException(DEFAULT_ADMIN_NOT_ALLOW_DELETE);
        }
        user.setStatus(false);
        this.userRepository.save(user);
        return new ResponseDTO(DELETE_SUCCESS);
    }

    /**
     * Check user exist ot not and return that value
     *
     * @param userId user's id
     * @return User
     * @throws RequestException User not found
     */
    private User checkExistingUser(int userId) throws RequestException {
        User user = this.userRepository.findByIdAndStatus(userId, true);
        if (user != null) {
            return user;
        } else {
            throw new RequestException(String.format(NOT_FOUND, userId));
        }
    }

    /**
     * Find available username
     *
     * @param users             Iterable<User>
     * @param username          String
     * @param existingNameCount int
     * @return String username
     */
    private String findAvailableName(Iterable<User> users, String username, int existingNameCount) {
        String finalUsername = username + existingNameCount;
        boolean isStillExist = StreamSupport.stream(users.spliterator(), false)
                .anyMatch(userSteam -> finalUsername.equals(userSteam.getUsername()));
        return isStillExist
                ? findAvailableName(users, finalUsername, existingNameCount + 1)
                : finalUsername;
    }
}
