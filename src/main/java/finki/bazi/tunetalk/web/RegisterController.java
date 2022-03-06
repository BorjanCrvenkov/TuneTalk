package finki.bazi.tunetalk.web;

import finki.bazi.tunetalk.service.UserService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;

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
    public String register(@RequestParam String fullName,
            @RequestParam("birthday") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate birthday,
            @RequestParam String email,
            @RequestParam(required = false) String mobilePhone,
            @RequestParam String username,
            @RequestParam String password,
            @RequestParam String repeatedPassword,
            @RequestParam(required = false) String aboutUser,
            @RequestParam String userImage,
                           Model model) {

        try {
            userService.createNewUser(username,password,repeatedPassword, fullName, email, mobilePhone,
                    birthday, aboutUser, userImage);
            return "redirect:/login";
        } catch (Exception ex) {
            model.addAttribute("error", ex.getMessage());
            model.addAttribute("bodyContent", "register");
            return "master-template";
        }

    }
}
