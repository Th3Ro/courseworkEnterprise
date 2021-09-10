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

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

/**
 * Admin controller class
 * @author Nosolenko
 * @version 1.0
 */
@Controller
public class AdminController {

    /** SportMatch entity repository for working with the database */
    @Autowired
    private SportMatchRepository sportMatchRepository;

    /** Bet entity repository for working with the database */
    @Autowired
    private BetRepository betRepository;

    /** User entity repository for working with the database */
    @Autowired
    private UserRepository userRepository;

    /**
     * Function for handling a get request at "/admin"
     * @param model - required attribute
     * @return returns jsp page admin
     */
    @GetMapping("/admin")
    public String index(Model model) {
        List<SportMatch> matches = sportMatchRepository.findAll();
        List<SportMatch> notEndMatches = new ArrayList<>();
        for(SportMatch match : matches){
            if(!match.isEnd()){
                notEndMatches.add(match);
            }
        }
        model.addAttribute("matches", notEndMatches);
        if (!MainController.isIsLogin() || MainController.getUserId() != 1){
            model.addAttribute("errorText", "Тебе сюда нельзя!");
            return "error";
        }
        return "admin";
    }

    /**
     * Function for processing a post request at the address "/match"
     * @param name - name of the match
     * @param date - date
     * @param koef1 - first coefficient
     * @param koef2 - втрой коэффициент
     * @param model - required attribute
     * @param redirectAttributes - attribute to interact with the redirect page model
     * @return returns redirection to "/admin"
     */
    @RequestMapping(value = "match", method = RequestMethod.POST)
    public String addMatch(@RequestParam("name") String name, @RequestParam("date") Date date,
                           @RequestParam("koef1") String koef1, @RequestParam("koef2") String koef2, Model model,
                           RedirectAttributes redirectAttributes){
        float firstKoef;
        float secondKoef;
        try{
            firstKoef = Float.parseFloat(koef1);
            secondKoef = Float.parseFloat(koef2);
        } catch (Exception ex){
            redirectAttributes.addFlashAttribute("error", "Вы ввели не вещественное значение");
            return "redirect:/admin";
        }
        SportMatch sportMatch = new SportMatch(name, date, firstKoef, secondKoef);
        sportMatchRepository.save(sportMatch);
        return "redirect:/admin";
    }

    /**
     * Function for handling a get request at "/changematch"
     * @param model - required attribute
     * @return returns redirection to "/admin"
     */
    @GetMapping("/changematch")
    public String changeMatch(Model model){
        if (!MainController.isIsLogin() || MainController.getUserId() != 1){
            model.addAttribute("errorText", "Тебе сюда нельзя!");
            return "error";
        }
        return "redirect:/admin";
    }

    /**
     * Function for processing a post request at the address "/match/{id}"
     * @param id - match id
     * @param model - required attribute
     * @param redirectAttributes - attribute to interact with the redirect page model
     * @return returns jsp page changematch
     */
    @RequestMapping(value = "/match/{id}", method = RequestMethod.POST)
    public String changeMatchById(@PathVariable(value = "id") Long id, Model model,
                                  RedirectAttributes redirectAttributes){
        SportMatch match = sportMatchRepository.findById(id).get();
        model.addAttribute("match", match);
        return "changematch";
    }

    /**
     * Post request processing function at "/changematch"
     * @param id - match id
     * @param team - team number
     * @param model - required attribute
     * @return returns redirection to "/admin"
     */
    @RequestMapping(value = "/changematch", method = RequestMethod.POST)
    public String saveChangesMatch(@RequestParam("id") Long id, @RequestParam("team") byte team, Model model){
        SportMatch match = sportMatchRepository.findById(id).get();
        match.setEnd(true);
        List<Bet> bets = betRepository.findAllByMatchId(id);
        for(Bet bet : bets){
            if(bet.getTeamNumber() == team){
                bet.setWinResult((byte) 1);
                User user = userRepository.findById(bet.getUserId()).get();
                if (team == 1){
                    user.setMoneyScore(user.getMoneyScore() + (bet.getMoney() *
                            sportMatchRepository.findById(bet.getMatchId()).get().getFirstCoefficient()));
                }
                else{
                    user.setMoneyScore(user.getMoneyScore() + (bet.getMoney() *
                            sportMatchRepository.findById(bet.getMatchId()).get().getSecondCoefficient()));
                }
                userRepository.save(user);
            }
            else{
                bet.setWinResult((byte) 2);
            }
            betRepository.save(bet);
        }
        sportMatchRepository.save(match);
        return "redirect:/admin";
    }
}
