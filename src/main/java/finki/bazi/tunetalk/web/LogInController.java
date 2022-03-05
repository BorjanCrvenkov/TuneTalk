package finki.bazi.tunetalk.web;


import finki.bazi.tunetalk.model.Users;
import finki.bazi.tunetalk.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/login")
public class LogInController {

    private final UserService userService;

    public LogInController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String getHomePage(Model model) {
        model.addAttribute("bodyContent", "login");
        return "master-template";
    }

    @PostMapping
    public String logIn(@RequestParam String username,
                        @RequestParam String password,
                        HttpServletRequest req,
                        Model model){

        try{
            Users user = userService.logIn(username, password);
            req.getSession().setAttribute("user",user);

            return "redirect:/home";

        }catch (Exception ex){
            model.addAttribute("error", ex.getMessage());
            model.addAttribute("bodyContent", "login");
            return "master-template";
        }




    }

}
