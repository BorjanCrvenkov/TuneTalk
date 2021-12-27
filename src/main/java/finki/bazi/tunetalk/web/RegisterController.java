package finki.bazi.tunetalk.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/register")
public class RegisterController {


    @GetMapping
    public String getRegisterPage(Model model) {
        model.addAttribute("bodyContent", "register");
        return "master-template";
    }

    private boolean verifyEmail(String email){
        return email.contains("@") && email.contains(".com"); // ako ima @ i .com validen e
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
                           @RequestParam(required = false) String aboutUser){


        if(!verifyEmail(email)){
//            throw exception();
        }

//        if(nesto od ova e null ili "" )
//        if(username exists)
//        if(email exists)
//        if(password!=repeatedPassword)

        return "redirect:/login";
    }
}
