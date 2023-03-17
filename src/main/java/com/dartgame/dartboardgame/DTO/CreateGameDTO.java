package com.dartgame.dartboardgame.DTO;

import com.dartgame.dartboardgame.entity.GameDetail;
import com.dartgame.dartboardgame.entity.GamerUser;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class CreateGameDTO {

    private String titleDesc;
    private Date timeToPlay;
    private GamerUser gamerUser;
    private GameDetail[] gameDetails;

}
