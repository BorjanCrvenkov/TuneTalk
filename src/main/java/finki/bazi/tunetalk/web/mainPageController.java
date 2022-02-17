package finki.bazi.tunetalk.web;

import finki.bazi.tunetalk.service.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping({"/","/home"})
public class mainPageController {
    private final AlbumService albumService;
    private final ArtistService artistService;
    private final SongService songService;
    private final GenreService genreService;
    private final CommentsService commentsService;
    private final UserService userService;
    private final SearchService searchService;


    public mainPageController(AlbumService albumService, ArtistService artistService, SongService songService, GenreService genreService, CommentsService commentsService, UserService userService, SearchService searchService) {
        this.albumService = albumService;
        this.artistService = artistService;
        this.songService = songService;
        this.genreService = genreService;
        this.commentsService = commentsService;
        this.userService = userService;
        this.searchService = searchService;
    }

    @GetMapping
    public String getHomePage(Model model, HttpServletRequest req) {

        model.addAttribute("bodyContent", "main-page");
        return "master-template";
    }

    @PostMapping("/search")
    public String search(@RequestParam String text,Model model) {

        List<Object> objects = searchService.searchByText(text);
        model.addAttribute("objects",objects);

        model.addAttribute("bodyContent", "main-page");
        return "master-template";
    }
}
