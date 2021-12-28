package finki.bazi.tunetalk.web;

import finki.bazi.tunetalk.service.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/user")
public class UserController {

    private final UserService userService;
    private final SongService songService;
    private final AlbumService albumService;
    private final ArtistService artistService;
    private final GenreService genreService;

    public UserController(UserService userService,SongService songService, AlbumService albumService, ArtistService artistService, GenreService genreService) {
        this.userService = userService;
        this.songService = songService;
        this.albumService = albumService;
        this.artistService = artistService;
        this.genreService = genreService;
    }

    @GetMapping
    public String getUserPage(Model model, HttpServletRequest req){

        model.addAttribute("user",req.getSession().getAttribute("user"));

        model.addAttribute("bodyContent", "user-page");
        return "master-template";
    }

}
