package net.winnings.repository;

import net.winnings.model.UserLoginData;
import org.springframework.data.repository.CrudRepository;

/**
 * Interface for interacting with the UserLoginData table of the database
 * @author Nosolenko
 * @version 1.0
 */
public interface UserLoginDataRepository extends CrudRepository<UserLoginData, Long> {
    /**
     * Function for obtaining authorization data of a user type {@link UserLoginData}
     * @param userLogin - user login
     * @return returns user credentials
     */
    UserLoginData findByUserLogin(String userLogin);
}
