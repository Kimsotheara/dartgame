package com.dartgame.dartboardgame.DTO.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Pattern;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CreateUserDto {

//    @NotEmpty(message = "fullName cannot be missing or empty!")
//    @Size(min = 2, max = 250, message = "fullName must not be less than 2 and greater than 250 characters!")
    private String fullName;

//    @NotEmpty(message = "username cannot be missing or empty!")
//    @Size(min = 2, max = 250, message = "username must not be less than 2 and greater than 250 characters!")
    private String username;

//    @NotEmpty(message = "password cannot be missing or empty!")
//    @Size(min = 4, max = 50, message = "password must not be less than 4 and greater than 50 characters!")
    private String password;

    @Pattern(regexp = "^true$|^false$", message = "status's value must be boolean type: true or false!")
    private String status = "true";

    private Date createDate = new Date();
    private Date modifyDate = new Date();

}
