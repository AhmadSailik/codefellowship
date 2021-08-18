package codefellowshipexample.codefellowship;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;
import org.thymeleaf.expression.Lists;

import javax.persistence.GeneratedValue;
import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class ApplicationUserController {
    @Autowired
    ApplicationUserRepository applicationUserRepository;
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    PostRepository postRepository;
    @Autowired
    FeedRepository feedRepository;

    @GetMapping("/signup")
    public String getSignUpPage(){
        return "signup";
    }
    @GetMapping("/login")
    public String getSignInPage(){
        return "signin";
    }
    @GetMapping("/")

    public String conect(){

        return "conect";
    }
    @GetMapping("/home")
    public String homePage(Model model,Principal principal){
    Iterable applicationUser=applicationUserRepository.findAll();
    model.addAttribute("user",applicationUser);
    String myName=principal.getName();
    model.addAttribute("myName",myName);
        return "home";
    }
    @GetMapping("/following")
    public String following(Principal principal,Model model){
        Iterable findAllByMyName= feedRepository.findAllByMyName(principal.getName());
        model.addAttribute("usersId",findAllByMyName);
        return "following";
    }
    @GetMapping("/profile")

    public String profile(Model model,Principal principal){
    ApplicationUser applicationUser=applicationUserRepository.findByUsername(principal.getName());
    Iterable addingPostId=postRepository.findAllByaddingPostId(applicationUser.getId());
    model.addAttribute("post",addingPostId);

    model.addAttribute("data",applicationUser);
       return "profile";
    }

    @GetMapping("/profile/{id}")
    public String getProfilePage(Principal principal, Model model, @PathVariable Integer id){
        ApplicationUser applicationUser=applicationUserRepository.findById(id).get();
        Iterable addingPostId=postRepository.findAllByaddingPostId(id);
        model.addAttribute("post",addingPostId);

        model.addAttribute("data",applicationUser);
        return "profileUser";
    }
    @GetMapping("/feed")

    public String feed(Principal principal,Model model){
        Iterable findAllByMyName= feedRepository.findAllByMyName(principal.getName());
        model.addAttribute("usersId",findAllByMyName);
        Iterable findAll=postRepository.findAll();
        model.addAttribute("userPost",findAll);
        return "feed";
    }




    @RequestMapping("/signup")
    @PostMapping("/signup")
    public RedirectView signUp(String username,String password,String firstName,String lastName,String birthday,String bio){
        ApplicationUser applicationUser=new ApplicationUser(username,bCryptPasswordEncoder.encode(password),firstName,lastName,birthday,bio);
        applicationUserRepository.save(applicationUser);
        return new RedirectView("/login");
    }
    @RequestMapping("/addPost")
    @PostMapping("/addPost")
    public RedirectView newPost(String body,Principal principal){
        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
        Date date = new Date(System.currentTimeMillis());
        String dateTime=formatter.format(date);

        int idUser=applicationUserRepository.findByUsername(principal.getName()).getId();
        ApplicationUser addingPost=applicationUserRepository.findById(idUser).get();
        Post post=new Post(body,dateTime,addingPost);
        postRepository.save(post);
        return new RedirectView("/profile");
    }
    @RequestMapping("/showPost")
    @PostMapping("/showPost")
    public RedirectView showPost(int id,Principal principal){
        String myName=principal.getName();
        ApplicationUser applicationUser=applicationUserRepository.findById(id).get();
        String userName=applicationUserRepository.findAllById(id).getUsername();
        Feed feed=new Feed(myName,userName, applicationUser);
        feedRepository.save(feed);
        return new RedirectView("/feed");
    }

}
