package net.winnings.repository;

import net.winnings.model.Bet;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Interface for interacting with the database rate table
 * @author Nosolenko
 * @version 1.0
 */
public interface BetRepository extends CrudRepository<Bet, Long> {
    /**
     * Function of receiving rate type {@link Bet}
     * @param userId - user id
     * @param matchId - match id
     * @return returns the rate
     */
    Bet findByUserIdAndAndMatchId(Long userId, Long matchId);

    /**
     * Function for getting a list of bets like {@link Bet}
     * @param userId - user id
     * @return returns a list of rates
     */
    List<Bet> findAllByUserId(Long userId);

    /**
     * Function for getting a list of bets like {@link Bet}
     * @param matchId - match id
     * @return returns a list of rates
     */
    List<Bet> findAllByMatchId(Long matchId);
}
