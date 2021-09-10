package net.winnings.controller;

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

/**
 * Authorization controller class
 * @author Nosolenko
 * @version 1.0
 */
@Controller
public class LoginController {

    /** Repository of the UserLoginData entity for working with the database */
    @Autowired
    private UserLoginDataRepository userLoginDataRepository;

    /**
     * Function for handling a get request at the address "/authorize"
     * @param model - required parameter
     * @return returns jsp authorize page
     */
    @GetMapping("/login")
    public String authorize(Model model){
        return "login";
    }

    /**
     * Post request processing function at "/ login"
     * @param login - login
     * @param password - password
     * @param model - required parameter
     * @param redirectAttributes - attribute to interact with the redirect page model
     * @return возвращает redirect to the main page
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String indexPost(@RequestParam("login") String login, @RequestParam("password") String password,
                            Model model, RedirectAttributes redirectAttributes){
        try{
            UserLoginData userLoginData = userLoginDataRepository.findByUserLogin(login);
            if(login.equals(userLoginData.getUserLogin()) && password.equals(userLoginData.getUserPassword())){
                MainController.setIsLogin(true);
                MainController.setUserId(userLoginData.getUserId());
            }
            else {
                redirectAttributes.addFlashAttribute("loginError", "Ошибка: Не верный логин или пароль.");
                return "redirect:/login";
            }
        }catch (Exception ex){
            System.out.println("Ошибка" + ex);
            redirectAttributes.addFlashAttribute("loginError", "Ошибка: Такого пользователя не существует.");
            return "redirect:/login";
        }
        return "redirect:/";
    }
}
