package net.winnings.repository;

import net.winnings.model.SportMatch;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Interface for interacting with the SportMatch database table
 * @author Nosolenko
 * @version 1.0
 */
public interface SportMatchRepository extends CrudRepository<SportMatch, Long> {
    /**
     * Function for getting a list of matches like {@link SportMatch}
     * @return returns a list of matches like
     */
    List<SportMatch> findAll();
}
