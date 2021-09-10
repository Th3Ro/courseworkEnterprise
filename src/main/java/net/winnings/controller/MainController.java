package net.winnings.controller;

import net.winnings.model.SportMatch;
import net.winnings.repository.SportMatchRepository;
import net.winnings.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Master controller class
 * @author Nosolenko
 * @version 1.0
 */
@Controller
public class MainController {

    /** User entity repository for working with the database */
    @Autowired
    private UserRepository userRepository;

    /** SportMatch entity repository for working with the database */
    @Autowired
    private SportMatchRepository sportMatchRepository;

    // THIS IS A BAD CODE, I KNOW IT
    /** A variable that stores the result of the user's entry (exit) to the site (from the site) */
    private static boolean isLogin = false;

    /** Variable that stores the id of the logged in user */
    private static Long userId;

    /**
     * Field value retrieval function {@link MainController#userId}
     * @return returns the id of the authorized user
     */
    public static Long getUserId() {
        return userId;
    }

    /**
     * The procedure for determining the id of an authorized user {@link MainController#userId}
     * @param userId - authorized user id
     */
    public static void setUserId(Long userId) {
        MainController.userId = userId;
    }

    /**
     * Field value retrieval function {@link MainController#isLogin}
     * @return returns the value of the user's authorization
     */
    public static boolean isIsLogin() {
        return isLogin;
    }

    /**
     * The procedure for determining the authorization of a user {@link MainController#isLogin}
     * @param isLogin - user authorization value
     */
    public static void setIsLogin(boolean isLogin) {
        MainController.isLogin = isLogin;
    }

    /**
     * Function for processing a get request at the address "/"
     * @param model - required parameter
     * @return returns jsp page index
     */
    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("isLogin", isLogin);
        List<SportMatch> matches = sportMatchRepository.findAll();
        ArrayList<SportMatch> actualMatches = new ArrayList<>();
        for(SportMatch match : matches){
            if(!match.isEnd()){
                actualMatches.add(match);
            }
        }
        if(matches.toArray().length != 0){
            model.addAttribute("matches", actualMatches);
        }
        if(isLogin){
            model.addAttribute("score", userRepository.findById(userId).get().getMoneyScore());
            model.addAttribute("id", userId);
        }
        return "index";
    }

    /**
     * Function for handling the get request at "/logout"
     * @param model - required parameter
     * @return redirects to address "/"
     */
    @GetMapping("/logout")
    public String logout(Model model){
        isLogin = false;
        userId = null;
        return "redirect:/";
    }

    /**
     * Function for processing a get request at the address "/error"
     * @param model - required parameter
     * @return returns jsp page error
     */
    @GetMapping("/error")
    public String error(Model model){
        return "error";
    }
}
