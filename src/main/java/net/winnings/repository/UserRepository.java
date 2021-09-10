package net.winnings.repository;

import net.winnings.model.User;
import org.springframework.data.repository.CrudRepository;

/**
 * Interface for interacting with the User table of the database
 * @author Nosolenko
 * @version 1.0
 */
public interface UserRepository extends CrudRepository<User, Long> {
}
