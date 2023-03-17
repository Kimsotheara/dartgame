package com.dartgame.dartboardgame.repository;

import com.dartgame.dartboardgame.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    User findByUsername(String username);
    Iterable<User> findAllByStatus(boolean status);
    Iterable<User> findByUsernameStartsWith(String username);
    User findByIdAndStatus(int id, boolean status);

}
