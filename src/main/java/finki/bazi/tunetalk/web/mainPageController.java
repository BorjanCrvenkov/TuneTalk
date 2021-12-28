package finki.bazi.tunetalk.web;

import finki.bazi.tunetalk.model.Users;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping(value = {"/", "/home","/TuneTalk","/tunetalk","/tuneTalk"})
public class mainPageController {

    @GetMapping
    public String getHomePage(Model model, HttpServletRequest req) {
        Users user = (Users) req.getSession().getAttribute("user");
        model.addAttribute("user",user);

        model.addAttribute("bodyContent", "main-page");
        return "master-template";
    }

}
