package com.sixtelmedia.Controllers;

import com.sixtelmedia.Entities.Actor;
import com.sixtelmedia.Entities.Film;
import com.sixtelmedia.Entities.FilmsActors;
import com.sixtelmedia.Entities.User;
import com.sixtelmedia.Services.ActorRepository;
import com.sixtelmedia.Services.FilmActorsRepository;
import com.sixtelmedia.Services.FilmRepository;
import com.sixtelmedia.Services.UserRepository;
import com.sixtelmedia.Utils.PasswordStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
    @Autowired
    FilmActorsRepository filmActorsRepository;


    @RequestMapping(path = "/", method = RequestMethod.GET)
    public String home(HttpSession session, Model model, boolean passMisMatch, boolean nameBad) {
        if (nameBad) {
            return "home";
        }
        if (passMisMatch) {
            return "home";
        }

        String userName = (String) session.getAttribute("userName");
        User user = userRepository.findByName(userName);


        List<Film> films = null;


        if (user != null) {
            if (filmRepository.count() > 0) {
                films = filmRepository.findByCreatedById(user.getId());
                for (Film f : films) {
                    f.setActors(filmActorsRepository.findActorByFilm(f));
                }
            }


            model.addAttribute("user", user); //add in the user
            model.addAttribute("film", films);
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
                return "redirect:/?passMisMatch=true"; //otherwise passwords do not match
            }
        } else {
            return "redirect:/?nameBad=true"; //otherwise user entered bad input
        }

    }



    @RequestMapping(path = "/createFilm", method = RequestMethod.POST)
    public String createFilm(HttpSession session, String movieName, String releaseYear, String actors) throws ParseException {
        User user = userRepository.findByName(session.getAttribute("userName").toString());

        List<Actor> actorsInDb = (List<Actor>) actorRepository.findAll();
        List<Actor> actorList = parseActors(actors);
        Film film = new Film(movieName, releaseYear, user);
        filmRepository.save(film);
        film = filmRepository.findByName(movieName);

        //if name is already in DB take it out of the array
        for (Actor a : actorsInDb) {
            a.setName(a.getName().trim());
            if (actorList.contains(a)) {
                actorList.remove(a);
            }
        }

        for (Actor a : actorList) {
            actorRepository.save(a);
            Actor actor = actorRepository.findByName(a.getName());
            filmActorsRepository.save(new FilmsActors(film, actor));
        }





        return "redirect:/";
    }

    @RequestMapping(path = "/edit", method = RequestMethod.GET)
    public String edit(Model model, int filmId) {

        Film film = filmRepository.findOne(filmId);

        model.addAttribute("film", film);

        return "edit";
    }

    @RequestMapping(path = "/editFilm", method = RequestMethod.POST)
    public String editFilm(String movieName, String releaseYear, String actors, int filmId) throws ParseException {

        Film film = filmRepository.findOne(filmId);

        film.setName(movieName);
        film.setReleaseYear(releaseYear);

        filmRepository.save(film);

        return "redirect:/";
    }

    @RequestMapping(path = "/deleteActor", method = RequestMethod.POST)
    public String deleteActor(int actorId, int filmId) {

        return "redirect:/";
    }

    public static ArrayList<Actor> parseActors(String actors) {
        ArrayList<Actor> actorList = new ArrayList<>();

        for (String s : Arrays.asList(actors.split(","))) {
            Actor a = new Actor(s);
            actorList.add(a);
        }
        return actorList;
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