package com.dartgame.dartboardgame.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Pattern;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class CreateGamerUserDTO {

    private String name;
    private String detail;

    @Pattern(regexp = "^true$|^false$", message = "status's value must be boolean type: true or false!")
    private String status = "true";

    private Date createDate = new Date();
    private Date modifyDate = new Date();

}
