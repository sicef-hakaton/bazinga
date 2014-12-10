package com.bazinga.hakaton.repository;

import com.bazinga.hakaton.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Ivan on 23.11.2014.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findUserByEmailAndPassword(String email, String password);

    User findUserByEmail(String email);

}
