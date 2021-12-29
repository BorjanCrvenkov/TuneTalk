package finki.bazi.tunetalk.web;

import finki.bazi.tunetalk.model.Users;
import finki.bazi.tunetalk.service.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/user")
public class UserController {

    private final UserService userService;
    private final SongService songService;
    private final AlbumService albumService;
    private final ArtistService artistService;
    private final GenreService genreService;
    private final CommentsService commentsService;

    public UserController(UserService userService, SongService songService, AlbumService albumService, ArtistService artistService, GenreService genreService, CommentsService commentsService) {
        this.userService = userService;
        this.songService = songService;
        this.albumService = albumService;
        this.artistService = artistService;
        this.genreService = genreService;
        this.commentsService = commentsService;
    }

    @GetMapping
    public String getUserPage(Model model, HttpServletRequest req){

        model.addAttribute("user",req.getSession().getAttribute("user"));

        model.addAttribute("bodyContent", "user-page");
        return "master-template";
    }

    @GetMapping("/album/{albumId}/like/{commentId}")
    public String likeAlbumComment(@PathVariable Integer albumId,@PathVariable Integer commentId, HttpServletRequest req){
        Users user = (Users) req.getSession().getAttribute("user");
        userService.likeComment(user.getUserId(),commentId);
        return "redirect:/albums/"+albumId;
    }

    @GetMapping("/album/{albumId}/dislike/{commentId}")
    public String dislikeAlbumComment(@PathVariable Integer albumId, @PathVariable Integer commentId, HttpServletRequest req){
        Users user = (Users) req.getSession().getAttribute("user");
        userService.dislikeComment(user.getUserId(),commentId);
        return "redirect:/albums/"+albumId;
    }

    @GetMapping("/song/{songId}/like/{commentId}")
    public String likeSongComment(@PathVariable Integer songId, @PathVariable Integer commentId, HttpServletRequest req){
        Users user = (Users) req.getSession().getAttribute("user");
        userService.likeComment(user.getUserId(),commentId);
        return "redirect:/songs/"+songId;
    }

    @GetMapping("/song/{songId}/dislike/{commentId}")
    public String dislikeSongComment(@PathVariable Integer songId, @PathVariable Integer commentId, HttpServletRequest req){
        Users user = (Users) req.getSession().getAttribute("user");
        userService.dislikeComment(user.getUserId(),commentId);
        return "redirect:/songs/"+songId;
    }



}
