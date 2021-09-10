package net.winnings.controller;

import net.winnings.model.Bet;
import net.winnings.model.User;
import net.winnings.repository.BetRepository;
import net.winnings.repository.SportMatchRepository;
import net.winnings.repository.UserRepository;
import net.winnings.view.BetForUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


/**
 * User profile controller class
 * @author Nosolenko
 * @version 1.0
 */
@Controller
public class ProfileController {

    /** User entity repository for working with the database */
    @Autowired
    private UserRepository userRepository;

    /** Bet entity repository for working with the database */
    @Autowired
    private BetRepository betRepository;

    /** SportMatch entity repository for working with the database */
    @Autowired
    private SportMatchRepository sportMatchRepository;

    /**
     * Function for processing a get request at the address "/profile"
     * @param model - required parameter
     * @return returns jsp profile page
     */
    @GetMapping("/profile")
    public String profile(Model model) {
        if (!MainController.isIsLogin()){
            model.addAttribute("errorText", "Сначала войди или зарегистрируйся.");
            return "error";
        }

        User user = userRepository.findById(MainController.getUserId()).get();
        model.addAttribute("id", user.getId());
        model.addAttribute("score", user.getMoneyScore());
        model.addAttribute("surname", user.getSurname());
        model.addAttribute("name", user.getName());
        model.addAttribute("dateOfBirth", user.getDateOfBirth());
        List<Bet> bets = betRepository.findAllByUserId(user.getId());
        ArrayList<BetForUser> fullBetList = new ArrayList<>();
        float coef = 0;
        for (Bet bet : bets){
            if(bet.getTeamNumber() == 1){
                coef = sportMatchRepository.findById(bet.getMatchId()).get().getFirstCoefficient();
            }
            else{
                coef = sportMatchRepository.findById(bet.getMatchId()).get().getSecondCoefficient();
            }
            fullBetList.add(new BetForUser(
                    bet.getMatchId(),
                    bet.getId(),
                    sportMatchRepository.findById(bet.getMatchId()).get().getName(),
                    bet.getTeamNumber(),
                    sportMatchRepository.findById(bet.getMatchId()).get().getDateOfMatch(),
                    coef,
                    bet.getMoney(),
                    bet.getWinResult()
            ));
        }
        model.addAttribute("betsInfo", fullBetList);
        if(user.getImage() != null){
            model.addAttribute("image", "/images/usersPic/" + user.getImage());
        }
        else{
            model.addAttribute("image", null);
        }
        return "profile";
    }

    /**
     * Function for processing a post request at "/upload"
     * @param photo - the photo
     * @param model - required attribute
     * @param redirectAttributes - attribute to interact with the redirect page model
     * @return returns redirection to "/ profile"
     */
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public String indexPost(@RequestParam("photo") MultipartFile photo, Model model,
                            RedirectAttributes redirectAttributes) {
        if(!photo.isEmpty()){
            String dir = System.getProperty("user.dir"); //путь к проекту
            String saveLocation = dir + "\\src\\main\\resources\\static\\images\\usersPic\\";
            String fileName = UUID.randomUUID().toString() + photo.getOriginalFilename();
            String location = saveLocation;
            File pathFile = new File(location);
            if (!pathFile.exists()) {
                pathFile.mkdir();
            }
            pathFile = new File(location + fileName);
            try {
                photo.transferTo(pathFile);
            } catch (IOException e) {
                redirectAttributes.addFlashAttribute("errorText", "Произошла ошибка добавления фотографии.");
                return "redirect:/error";
            }
            User user = userRepository.findById(MainController.getUserId()).get();
            user.setImage(fileName);
            userRepository.save(user);
        }
        else{
            redirectAttributes.addFlashAttribute("error", "Вы не выбрали фотографию.");
        }
        return "redirect:/profile";
    }

    /**
     * Post request processing function at "/saveChanges"
     * @param surname - surname
     * @param name - name
     * @param date - date of birth
     * @param model - required attribute
     * @param redirectAttributes - attribute to interact with the redirect page model
     * @return returns a redirect to "/profile"
     */
    @RequestMapping(value = "/saveChanges", method = RequestMethod.POST)
    public String saveChanges(@RequestParam("surname") String surname, @RequestParam("name") String name,
                              @RequestParam("date") Date date, Model model, RedirectAttributes redirectAttributes) {
        try{
            User user = userRepository.findById(MainController.getUserId()).get();
            user.setSurname(surname);
            user.setName(name);
            user.setDateOfBirth(date);
            return "redirect:/profile";
        } catch (Exception ex){
            return "error";
        }
    }

    /**
     * Post request processing function at "/addMoney"
     * @param model - required attribute
     * @param redirectAttributes - attribute to interact with the redirect page model
     * @return returns redirection to address "/"
     */
    @RequestMapping(value = "/addMoney", method = RequestMethod.POST)
    public String addMoney(Model model, RedirectAttributes redirectAttributes) {
        try{
            User user = userRepository.findById(MainController.getUserId()).get();
            double nowScore = user.getMoneyScore();
            if (nowScore < 20.0){
                user.setMoneyScore(nowScore + 300);
                userRepository.save(user);
                redirectAttributes.addFlashAttribute("score", user.getMoneyScore());
                return "redirect:/";
            }
        } catch (Exception ex){
            return "error";
        }
        redirectAttributes.addFlashAttribute("errorText", "У Вас достаточно денег, не наглейте" +
                " нам тоже надо что-то есть.");
        return "redirect:/error";
    }
}
