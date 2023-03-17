package com.dartgame.dartboardgame.DTO;

import com.dartgame.dartboardgame.entity.Game;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class CreateGameDetailDTO {

    private Game game;
    private int score;

}
