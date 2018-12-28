package com.jessica.codefellowship.applicationUsers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Optional;

@Controller
public class ApplicationUserController {

    @Autowired
    ApplicationUserRepository AppUserRepo;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    //direct to the splash page
    @RequestMapping(value="/", method= RequestMethod.GET)
//    @ResponseBody //for testing
    public String indexHome(Model m) {
        m.addAttribute("user", false);
        return "index";
    }

    //direct to the login page
    @RequestMapping(value="/login", method= RequestMethod.GET)
//    @ResponseBody //for testing
    public String indexLogin(Model m) {
        m.addAttribute("user", false);
        return "login";
    }

    // Login form with error
    @RequestMapping("/login-error")
    public String loginError(Model m) {
        m.addAttribute("loginError", true);
        m.addAttribute("user", false);
        return "login";
    }

    //direct to the signup page
    @RequestMapping(value="/signup", method= RequestMethod.GET)
//    @ResponseBody  //for testing
    public String index(Model m) {
        m.addAttribute("user", false);
        return "signup";
    }

    //creating a new user and saving to the db
    @RequestMapping(value="/signup", method= RequestMethod.POST)
    public RedirectView create(@RequestParam String username, @RequestParam String password,
                         @RequestParam String firstName, @RequestParam String lastName,
                         @RequestParam String dateOfBirth, @RequestParam String bio) {

        //create and save new user to db
        ApplicationUser newUser = new ApplicationUser(username, bCryptPasswordEncoder.encode(password), firstName, lastName, dateOfBirth, bio);
        AppUserRepo.save(newUser);

        //auto-login after creating an account
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(newUser, null, new ArrayList<>());
        SecurityContextHolder.getContext().setAuthentication(authentication);

        return new RedirectView("/users/" + newUser.id);
    }

    //Display any users' profile
    @RequestMapping(value="/users/{id}", method=RequestMethod.GET)
    public String show(@PathVariable long id, Model m, Principal p) {

        //ERROR HANDLING HERE verify that there is something to get

        ApplicationUser user = (ApplicationUser) ((UsernamePasswordAuthenticationToken) p).getPrincipal();

        m.addAttribute("user", AppUserRepo.findById(id).get());
        m.addAttribute("myProfile", false);
        m.addAttribute("userId", user.id);
        return "profile";
    }

    //display the logged in user's profile
    @RequestMapping(value="/myprofile")
    public String myProfile(Principal p, Model m) {

        ApplicationUser user = (ApplicationUser) ((UsernamePasswordAuthenticationToken) p).getPrincipal();

        m.addAttribute("user", AppUserRepo.findById(user.id).get());
        m.addAttribute("myProfile", true);
        m.addAttribute("userId", user.id);
        return "profile";
    }

    //Method to display all users
    @RequestMapping(value="/users", method=RequestMethod.GET)
    public String getUsers(Principal p, Model m){

        ApplicationUser user = (ApplicationUser) ((UsernamePasswordAuthenticationToken) p).getPrincipal();

        m.addAttribute("allUsers", AppUserRepo.findAll());
        m.addAttribute("user", AppUserRepo.findById(user.id).get());
        return "users";
    }


    //To follow a user
    @RequestMapping(value="/users/{id}/follow")
    public RedirectView followAUser(@PathVariable long id, Principal p, Model m){

        //find the followUser (id) that I am following
        ApplicationUser user = (ApplicationUser) ((UsernamePasswordAuthenticationToken) p).getPrincipal();

        //add follower (principal) to followers (add me to their followers)
        Optional<ApplicationUser> follower = AppUserRepo.findById(id);
        Optional<ApplicationUser> following = AppUserRepo.findById(user.id);

        ApplicationUser followerUser = following.get();
        followerUser.following.add(follower.get());
        AppUserRepo.save(followerUser);
        m.addAttribute("user", AppUserRepo.findById(user.id).get());

        return new RedirectView("/users/" + id);
    }

    //To view a user's feed of posts
    @RequestMapping(value="/feed", method=RequestMethod.GET)
    public String allPosts(Model m, Principal p) {

        ApplicationUser user = (ApplicationUser) ((UsernamePasswordAuthenticationToken) p).getPrincipal();
        m.addAttribute("user", AppUserRepo.findById(user.id).get());
        return "feed";
    }


}
