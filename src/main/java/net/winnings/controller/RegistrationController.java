package net.winnings.controller;

import net.winnings.model.User;
import net.winnings.model.UserLoginData;
import net.winnings.repository.UserLoginDataRepository;
import net.winnings.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.sql.Date;

/**
 * Registration controller class
 * @author Nosolenko
 * @version 1.0
 */
@Controller
public class RegistrationController {

    /** Repository of the UserLoginData entity for working with the database */
    @Autowired
    private UserLoginDataRepository userLoginDataRepository;

    /** User entity repository for working with the database */
    @Autowired
    private UserRepository userRepository;

    /**
     * Function for handling a get request at the address "/registration"
     * @param model - required parameter
     * @return returns the jsp registration page
     */
    @GetMapping("/registration")
    public String register(Model model){
        return "registration";
    }

    /**
     * Function for handling the request at "/registration"
     * @param login - login
     * @param password - password
     * @param surname - surname
     * @param name - name
     * @param date - date of birth
     * @param model - required parameter
     * @param redirectAttributes - attribute to interact with the redirect page model
     * @return returns redirection to address "/"
     */
    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String indexPost(@RequestParam("login") String login, @RequestParam("password") String password,
                            @RequestParam("surname") String surname, @RequestParam("name") String name,
                            @RequestParam("date") Date date, Model model, RedirectAttributes redirectAttributes) {
        try{
            UserLoginData userDate = userLoginDataRepository.findByUserLogin(login);
            if (userDate != null){
                redirectAttributes.addFlashAttribute("errorRegistration", "Ошибка: Пользователь с таким логином уже существует");
                return "redirect:/registration";
            }
        } catch (Exception ex){
        }
        User newUser = new User(surname, name, date, 0L);
        userRepository.save(newUser);
        UserLoginData newUserData = new UserLoginData(login, password, newUser.getId());
        userLoginDataRepository.save(newUserData);
        return "redirect:/";
    }
}
