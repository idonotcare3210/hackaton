package com.blindcats.lifecourse.repository;

import com.blindcats.lifecourse.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
    List<User> findByUsernameLike(String username);
    List<User> findByFirstnameLike(String firstname);
    List<User> findByMiddlenameLike(String middlename);
    List<User> findByLastnameLike(String lastname);
    User findByEmail(String email);
}
