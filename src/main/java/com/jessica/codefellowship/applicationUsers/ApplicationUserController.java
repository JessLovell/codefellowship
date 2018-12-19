package com.jessica.codefellowship.applicationUsers;

import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
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

import java.util.ArrayList;

@Controller
public class ApplicationUserController {

    @Autowired
    ApplicationUserRepository AppUserRepo;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    protected String username;
    protected String password;
    protected String firstName;
    protected String lastName;
    protected String dateOfBirth;
    protected String bio;

    @RequestMapping(value="/signup", method= RequestMethod.GET)
    public String index() {
        return "signup";
    }

    @RequestMapping(value="/signup", method= RequestMethod.POST)
    public RedirectView create(@RequestParam String username, @RequestParam String password,
                         @RequestParam String firstName, @RequestParam String lastName,
                         @RequestParam String dateOfBirth, @RequestParam String bio) {

        ApplicationUser newUser = new ApplicationUser(username, bCryptPasswordEncoder.encode(password), firstName, lastName, dateOfBirth, bio);

        AppUserRepo.save(newUser);

        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(newUser, null, new ArrayList<>());
        SecurityContextHolder.getContext().setAuthentication(authentication);

        return new RedirectView("/users/" + newUser.id);
    }

    @RequestMapping(value="/users/{id}", method=RequestMethod.GET)
    public String show(@PathVariable long id, Model m) {

        m.addAttribute("user", AppUserRepo.findById(id).get());
        return "profile";
    }
}
