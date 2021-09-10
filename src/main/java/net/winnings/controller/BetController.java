package net.winnings.controller;

import net.winnings.model.Bet;
import net.winnings.model.SportMatch;
import net.winnings.model.User;
import net.winnings.repository.BetRepository;
import net.winnings.repository.SportMatchRepository;
import net.winnings.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


/**
 * Bet controller class
 * @author Nosolenko
 * @version 1.0
 */
@Controller
public class BetController {

    /** User entity repository for working with the database */
    @Autowired
    private UserRepository userRepository;

    /** SportMatch entity repository for working with the database */
    @Autowired
    private SportMatchRepository sportMatchRepository;

    /** Bet entity repository for working with the database */
    @Autowired
    private BetRepository betRepository;

    /**
     * Function for processing a post request at the address "/bet/{id}"
     * @param id - match id
     * @param model - required attribute
     * @param redirectAttributes - attribute to interact with the redirect page model
     * @return returns redirect to "/makebet"
     */
    @RequestMapping(value = "/bet/{id}", method = RequestMethod.POST)
    public String openBetPage(@PathVariable(value = "id") Long id,
                              Model model,
                              RedirectAttributes redirectAttributes) {
        try{
            Bet findBet = betRepository.findByUserIdAndAndMatchId(MainController.getUserId(), id);
            if(findBet != null){
                redirectAttributes.addFlashAttribute("message", "Вы уже поставили на этот матч");
                return "redirect:/";
            }
        }catch(Exception e){
            System.out.println("Произошла ошибка " + e);
        }

        SportMatch sportMatch = sportMatchRepository.findById(id).get();
        redirectAttributes.addFlashAttribute("match", sportMatch);
        redirectAttributes.addFlashAttribute("score",
                userRepository.findById(MainController.getUserId()).get().getMoneyScore());
        return "redirect:/makebet";
    }

    /**
     * Function for handling get request at "/makebet"
     * @param model - required attribute
     * @param redirectAttributes - attribute to interact with the redirect page model
     * @return returns jsp makebet page
     */
    @GetMapping("/makebet")
    public String makeBet(Model model,
                          RedirectAttributes redirectAttributes) {
        if (!MainController.isIsLogin()){
            model.addAttribute("errorText", "Сначала войди или зарегистрируйся.");
            return "error";
        }
        return "makebet";
    }

    /**
     * Post request processing function at "/makebet"
     * @param id - match id
     * @param money - stake amount
     * @param team - the team on which the bet was made
     * @param model - required attribute
     * @param redirectAttributes - attribute to interact with the redirect page model
     * @return returns redirection to address "/"
     */
    @RequestMapping(value = "/makebet", method = RequestMethod.POST)
    public String afterMakeBet(@RequestParam("id") Long id, @RequestParam("money") int money,
                               @RequestParam("team") byte team, Model model, RedirectAttributes redirectAttributes){
        User user = userRepository.findById(MainController.getUserId()).get();

        Bet bet;
        if(money > user.getMoneyScore()){
            SportMatch sportMatch = sportMatchRepository.findById(id).get();
            redirectAttributes.addFlashAttribute("error", "У вас не хватает денег");
            redirectAttributes.addFlashAttribute("match", sportMatch);
            redirectAttributes.addFlashAttribute("score", user.getMoneyScore());
            return "redirect:/makebet";
        }
        try{
            user.setMoneyScore(user.getMoneyScore() - money);
            userRepository.save(user);
            bet = new Bet(id, user.getId(), team, money);
            betRepository.save(bet);
        }catch(Exception ex){
            redirectAttributes.addFlashAttribute("message", "При попытке сделать ставку произошла ошибка");
            return "redirect:/";
        }
        redirectAttributes.addFlashAttribute("message", "Вы успешно сделали ставку.");
        return "redirect:/";
    }
}
