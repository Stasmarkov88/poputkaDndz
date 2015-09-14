package com.easygoapp.repository;

import com.easygoapp.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Stanislav Markov mailto: stasmarkov88@gmail.com
 */

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByLogin(String login);
}
