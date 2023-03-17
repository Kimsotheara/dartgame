package com.dartgame.dartboardgame.repository;

import com.dartgame.dartboardgame.entity.Test;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TestRepository extends JpaRepository<Test,Integer> {
}
