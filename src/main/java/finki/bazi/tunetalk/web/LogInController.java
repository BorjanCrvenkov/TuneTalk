package finki.bazi.tunetalk.web;


import finki.bazi.tunetalk.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;

@Controller
@RequestMapping("/login")
public class LogInController {

    @GetMapping
    public String getHomePage(Model model) {
        model.addAttribute("bodyContent", "login");
        return "master-template";
    }

    @PostMapping
    public String logIn(@RequestParam String username,
                        @RequestParam String password,
                        HttpServletRequest req){

        User user = new User(username,password,"Name","Surname","email@email.com", LocalDate.now(),"",20,"");
        req.getSession().setAttribute("user",user);


        return "redirect:/home";
    }

}
