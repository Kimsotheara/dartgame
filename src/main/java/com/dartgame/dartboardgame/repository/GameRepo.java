package com.dartgame.dartboardgame.repository;

import com.dartgame.dartboardgame.entity.Game;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameRepo extends JpaRepository<Game,Integer> {
}
