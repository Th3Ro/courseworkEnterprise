package net.winnings.repository;

import net.winnings.model.Bet;
import org.springframework.data.repository.CrudRepository;

/**
 * Interface for interacting with the database rate table
 * @author Nosolenko
 * @version 1.0
 */
public interface BetRepository extends CrudRepository<Bet, Long> {
}
