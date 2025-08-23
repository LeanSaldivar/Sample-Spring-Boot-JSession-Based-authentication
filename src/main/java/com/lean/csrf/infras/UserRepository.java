package com.lean.csrf.infras;

import com.lean.csrf.view.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);

    boolean existsByUserName(String userName);
    boolean existsByEmail(String email);


}
