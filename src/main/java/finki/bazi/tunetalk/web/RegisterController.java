package finki.bazi.tunetalk.web;

import finki.bazi.tunetalk.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/register")
public class RegisterController {

    private final UserService userService;

    public RegisterController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String getRegisterPage(Model model) {
        model.addAttribute("bodyContent", "register");
        return "master-template";
    }

    @PostMapping
    public String register(@RequestParam String name,
                           @RequestParam String surname,
                           @RequestParam int age,
                           @RequestParam String email,
                           @RequestParam(required = false) String mobilePhone,
                           @RequestParam String username,
                           @RequestParam String password,
                           @RequestParam String repeatedPassword,
                           @RequestParam(required = false) String aboutUser,Model model){

        try{
            userService.createNewUser(name, surname, age, email, mobilePhone, username, password, repeatedPassword, aboutUser);
            return "redirect:/login";
        }catch (Exception ex){
            model.addAttribute("error", ex.getMessage());
            model.addAttribute("bodyContent", "register");
            return "master-template";
        }


    }
}
