package com.jessica.codefellowship.posts;

import com.jessica.codefellowship.applicationUsers.ApplicationUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

import java.security.Principal;
import java.util.Date;

@Controller
public class PostController {

    @Autowired
    PostRepository postRepo;

    @Autowired
    ApplicationUser appUserRepo;

    @RequestMapping(value="/post", method=RequestMethod.POST)
    public RedirectView create(@RequestParam String body, Principal p) {

        ApplicationUser user = (ApplicationUser) ((UsernamePasswordAuthenticationToken) p).getPrincipal();
        //create a post & save it
        Post newPost = new Post(body, new Date());
        newPost.appUser = appUserRepo;
        postRepo.save(newPost);

        //redirect to myprofile
        return new RedirectView("/myprofile");
    }
}
