package com.dartgame.dartboardgame.repository;

import com.dartgame.dartboardgame.entity.GameDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameDetailRepo extends JpaRepository<GameDetail, Integer> {
}
