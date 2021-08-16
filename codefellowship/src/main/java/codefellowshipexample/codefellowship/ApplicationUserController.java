package codefellowshipexample.codefellowship;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import javax.persistence.GeneratedValue;
import java.security.Principal;

@Controller
public class ApplicationUserController {
    @GetMapping("/")

    public String conect(){

        return "conect";
    }
    @GetMapping("/home")
    public String homePage(){

        return "home";
    }
    @GetMapping("/profile")
//    @ResponseBody
    public String profile(Model model,Principal principal){
    ApplicationUser applicationUser=applicationUserRepository.findByUsername(principal.getName());
    Iterable addingPostId=postRepository.findAllByaddingPostId(applicationUser.getId());
    model.addAttribute("post",addingPostId);

    model.addAttribute("data",applicationUser);
       return "profile";
    }
    @Autowired
    ApplicationUserRepository applicationUserRepository;
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    PostRepository postRepository;
    @GetMapping("/signup")
    public String getSignUpPage(){
        return "signup";
    }
    @GetMapping("/login")
    public String getSignInPage(){
        return "signin";
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
        int idUser=applicationUserRepository.findByUsername(principal.getName()).getId();
        ApplicationUser addingPost=applicationUserRepository.findById(idUser).get();
        Post post=new Post(body,addingPost);
        postRepository.save(post);
        return new RedirectView("/profile");
    }
}
