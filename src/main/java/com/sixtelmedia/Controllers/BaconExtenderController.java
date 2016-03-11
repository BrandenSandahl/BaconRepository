package com.sixtelmedia.Controllers;

import com.sixtelmedia.Entities.Actor;
import com.sixtelmedia.Entities.Film;
import com.sixtelmedia.Entities.User;
import com.sixtelmedia.Services.ActorRepository;
import com.sixtelmedia.Services.FilmRepository;
import com.sixtelmedia.Services.UserRepository;
import com.sixtelmedia.Utils.PasswordStorage;
import javafx.util.converter.LocalDateTimeStringConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Year;
import java.time.format.DateTimeFormatter;

/**
 * Created by branden on 3/10/16 at 18:36.
 */
@Controller
public class BaconExtenderController {

    @Autowired
    ActorRepository actorRepository;
    @Autowired
    FilmRepository filmRepository;
    @Autowired
    UserRepository userRepository;


    @RequestMapping(path = "/", method = RequestMethod.GET)
    public String home(HttpSession session, Model model, boolean passMisMatch, boolean nameBad) {
        if (nameBad == true) {
            return "home";
        }
        if (passMisMatch == true) {
            return "home";
        }

        String userName = (String) session.getAttribute("userName");
        String actorName = (String) session.getAttribute("actorName");
        User user = userRepository.findByName(userName);
        Actor actor = actorRepository.findByName(actorName);

        if (user != null) {
            model.addAttribute("user", user); //add in the user
        }
        if (actor != null) {
            model.addAttribute("actor", actor);
        }


        return "home";
    }

    @RequestMapping(path = "/login", method = RequestMethod.POST)
    public String login(HttpSession session, String userName, String userPassword) throws PasswordStorage.CannotPerformOperationException {
        //lets just make sure there has been data entered in both boxes
        if (userName != null && !userName.equals("") && userPassword != null && !userPassword.equals("")) { //check for input
            User user = userRepository.findByName(userName); //check for existing user

            if (user == null) {  //if no exisiting user
                user = new User(userName, PasswordStorage.createHash(userPassword)); //create a user and hash password
                userRepository.save(user); //save the user to DB
                session.setAttribute("userName", user.getName());  //set session
                return "redirect:/"; //done

            } else if (!user.getPassword().equals(PasswordStorage.createHash(userPassword))) { //if the password matches pass in DB
                session.setAttribute("userName", user.getName()); //set session
                return "redirect:/"; //done
            } else {
                return "redirect:/?passMisMatch=true"; //otherwise paswords do not match
            }
        } else {
            return "redirect:/?nameBad=true"; //otherwise user entered bad input
        }

    }


    @RequestMapping(path = "/createActor", method = RequestMethod.POST)
    public String createActor(HttpSession session, String actorName) {
        User user = userRepository.findByName(session.getAttribute("userName").toString());

        Actor actor = new Actor(actorName);
        actorRepository.save(actor);

        session.setAttribute("actorName", actor.getName());

        return "redirect:/";
    }

    @RequestMapping(path = "/createFilm", method = RequestMethod.POST)
    public String createFilm(HttpSession session, String movieName, String releaseYear, String actorConnection) throws ParseException {
        //Actor actor = actorRepository.findByName(session.getAttribute("actorName").toString());
        Actor actor = new Actor(actorConnection);
        actorRepository.save(actor);

        Year year = Year.parse(releaseYear);

        LocalDate year1 = LocalDate.from(year);



        Film film = new Film(movieName, year1);
        film.setActor(actor);
        filmRepository.save(film);

        return "redirect:/";
    }





//    //checks a userName. Returns true if it's ok
    //not currently implemented.
//    public static boolean nameChecker(String userName) {
//        if (userName.contains(" ") || userName.matches("^[^a-zA-Z0-9_]$")) {
//            return false;
//        } else {
//            return true;
//        }
//    }
}