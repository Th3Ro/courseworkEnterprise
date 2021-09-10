package net.winnings.repository;

import net.winnings.model.SportMatch;
import org.springframework.data.repository.CrudRepository;

/**
 * Interface for interacting with the SportMatch database table
 * @author Nosolenko
 * @version 1.0
 */
public interface SportMatchRepository extends CrudRepository<SportMatch, Long> {
}
