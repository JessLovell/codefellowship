package com.jessica.codefellowship.posts;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class PostController {

    @Autowired
    PostRepository postRepo;

    @RequestMapping(value="/post", method=RequestMethod.POST)
    public RedirectView create(@RequestParam String body) {

        Date createdAt = new Date();

        //create a post & save it
        Post newPost = new Post(body, createdAt);
        postRepo.save(newPost);

        //redirect to myprofile
        return new RedirectView("/myprofile");
    }
}
