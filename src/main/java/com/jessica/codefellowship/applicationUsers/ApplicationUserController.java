package com.jessica.codefellowship.applicationUsers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;
import java.security.Principal;
import java.util.ArrayList;

@Controller
public class ApplicationUserController {

    @Autowired
    ApplicationUserRepository AppUserRepo;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @RequestMapping(value="/login", method= RequestMethod.GET)
    public String indexLogin() {
        return "login";
    }

    @RequestMapping(value="/signup", method= RequestMethod.GET)
    public String index() {
        return "signup";
    }

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

    @RequestMapping(value="/users/{id}", method=RequestMethod.GET)
    public String show(@PathVariable long id, Model m) {

        //ERROR HANDLING HERE verify that there is something to get


        m.addAttribute("user", AppUserRepo.findById(id).get());
        m.addAttribute("myProfile", false);
        return "profile";
    }

    @RequestMapping(value="/myprofile")
    public String myProfile(Principal p, Model m) {

        ApplicationUser user = (ApplicationUser) ((UsernamePasswordAuthenticationToken) p).getPrincipal();

        m.addAttribute("user", AppUserRepo.findById(user.id).get());
        m.addAttribute("myProfile", true);
        return "profile";
    }


}
