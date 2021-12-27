package finki.bazi.tunetalk.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/logout")
public class LogOutController {

    @GetMapping
    public String getHomePage(Model model, HttpServletRequest req) {
        req.getSession().invalidate();
        return "redirect:/login";
    }
}
