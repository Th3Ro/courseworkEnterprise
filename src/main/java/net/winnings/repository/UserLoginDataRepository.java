package net.winnings.repository;

import net.winnings.model.UserLoginData;
import org.springframework.data.repository.CrudRepository;

/**
 * Interface for interacting with the UserLoginData table of the database
 * @author Nosolenko
 * @version 1.0
 */
public interface UserLoginDataRepository extends CrudRepository<UserLoginData, Long> {
}
