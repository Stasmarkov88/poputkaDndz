package com.easygoapp.service;

import com.easygoapp.domain.User;
import org.springframework.stereotype.Service;

/**
 * Created by Станислав on 28.02.2015.
 */
@Service
public interface UserService extends AbstractCrudService<User, Long> {

    User getByLogin(String login);
}
