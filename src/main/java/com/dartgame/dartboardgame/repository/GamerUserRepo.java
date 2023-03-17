package com.dartgame.dartboardgame.repository;

import com.dartgame.dartboardgame.entity.GamerUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface GamerUserRepo extends JpaRepository<GamerUser,Integer> {

    Iterable<GamerUser> findAllByStatus(boolean status);
    Optional<GamerUser> findByName(String name);

}
