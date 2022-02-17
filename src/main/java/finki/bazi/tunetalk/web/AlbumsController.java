package finki.bazi.tunetalk.web;


import finki.bazi.tunetalk.model.*;
import finki.bazi.tunetalk.service.*;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/albums")
public class AlbumsController {

    private final AlbumService albumService;
    private final ArtistService artistService;
    private final SongService songService;
    private final GenreService genreService;
    private final CommentsService commentsService;
    private final UserService userService;

    public AlbumsController(AlbumService albumService, ArtistService artistService, SongService songService, GenreService genreService, CommentsService commentsService, UserService userService) {
        this.albumService = albumService;
        this.artistService = artistService;
        this.songService = songService;
        this.genreService = genreService;
        this.commentsService = commentsService;
        this.userService = userService;
    }

    @GetMapping
    public String getAllAlbumsPage(Model model, HttpServletRequest req){
        Users user = (Users) req.getSession().getAttribute("user");
        model.addAttribute("user",user);

        model.addAttribute("albumList",albumService.findAllAlbums());

        model.addAttribute("bodyContent", "list-albums");
        return "master-template";
    }

    @GetMapping("/create")
    public String getCreateNewAlbumPage(Model model){
        model.addAttribute("artistList",artistService.findAll());


        model.addAttribute("bodyContent", "create-album");
        return "master-template";
    }

    @PostMapping("/create")
    public String createNewAlbum(@RequestParam String albumName,
                                 @RequestParam("dateReleased") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateReleased,
                                 @RequestParam float rating,
                                 @RequestParam int artistId){

        albumService.createNewAlbum(albumName,dateReleased,rating,artistId);

        return "redirect:/artists";
    }

    @GetMapping("/{id}")
    public String getAlbumPage(Model model, @PathVariable int id){
        Album album = albumService.findById(id);
        if(album == null){
            return "redirect:/home";
        }
        model.addAttribute("album",album);

        List<Artist> artists = artistService.getArtistsByAlbumId(id);
        model.addAttribute("artists",artists);

        List<Song> songs = songService.findAllSongsByAlbumId(id);
        model.addAttribute("songs",songs);

        model.addAttribute("genres", genreService.findGenresByAlbumId(id));

        List<Comment> comments = commentsService.findCommentsByAlbumId(id);
        model.addAttribute("comments",comments);

        model.addAttribute("bodyContent", "album-page");
        return "master-template";
    }

    @GetMapping("/edit/{id}")
    public String getAlbumEditPage(Model model, @PathVariable int id){
        Album album = albumService.findById(id);
        model.addAttribute("album",album);

        model.addAttribute("artists", artistService.getArtistsByAlbumId(id));

        model.addAttribute("artistsList", artistService.getArtistsByAlbumId(id));

        model.addAttribute("genres", genreService.findGenresByAlbumId(id));

        model.addAttribute("bodyContent", "edit-album");
        return "master-template";
    }

    @PostMapping("/edit/{id}")
    public String AlbumEditPage(@PathVariable Integer id,
                                @RequestParam String albumName,
                                @RequestParam("dateReleased") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateReleased,
                                @RequestParam float rating){
        albumService.updateAlbum(id, albumName, dateReleased, rating);
        return "redirect:/albums/" + id;
    }

    @PostMapping("/search")
    public String albumSearch(@RequestParam int albumId){
        Album album = albumService.findById(albumId);
        if(album != null){
            return "redirect:/albums/"+albumId;
        }else{
            return "redirect:/albums";
        }
    }

    @GetMapping("/addArtist/{id}")
    public String addAlbum(@PathVariable int id,Model model){
        Album album = albumService.findById(id);
        model.addAttribute("album",album);

        model.addAttribute("artistsList", artistService.findAll());

        model.addAttribute("bodyContent", "addArtistToAlbum");
        return "master-template";
    }

    @PostMapping("/addArtist/{id}")
    public String addAlbumPost(@PathVariable int id,
                                @RequestParam Integer artistId){

        artistService.addArtistToAlbum(artistId,id);
        return "redirect:/albums/"+id;
    }

    @GetMapping("/deleteArtistFromAlbum/{albumId}/{artistId}")
    public String deleteArtistInAlbum(@PathVariable Integer albumId,@PathVariable Integer artistId){
        albumService.deleteArtistFromAlbum(artistId,albumId);
        return "redirect:/albums/"+albumId;
    }

    @GetMapping("/deleteSongFromAlbum/{albumId}/{songId}")
    public String deleteSongFromAlbum(@PathVariable Integer albumId,@PathVariable Integer songId){
        albumService.deleteSongFromAlbum(songId,albumId);
        return "redirect:/albums/"+albumId;
    }

    @GetMapping("/addGenre/{id}")
    public String addGenre(@PathVariable int id,Model model){

        Album album = albumService.findById(id);
        model.addAttribute("album",album);

        model.addAttribute("genres", genreService.listAllGenres());

        model.addAttribute("bodyContent", "addGenreToAlbum");
        return "master-template";
    }

    @PostMapping("/addGenre/{id}")
    public String addGenrePost(@PathVariable int id,
                                @RequestParam Integer genreId){

        albumService.addGenreToAlbum(genreId,id);
        return "redirect:/albums/"+id;
    }


    @GetMapping("/deleteGenreFromAlbum/{albumId}/{genreId}")
    public String deleteGenreFromAlbum(@PathVariable Integer albumId,@PathVariable Integer genreId){
        albumService.deleteGenreFromAlbum(genreId,albumId);
        return "redirect:/albums/"+albumId;
    }

    @GetMapping("/addComment/{albumId}")
    public String addAlbumComment(@PathVariable Integer albumId,
                                  @RequestParam String text,HttpServletRequest req){
        Users user = (Users) req.getSession().getAttribute("user");
        commentsService.createNewComment(text,null,user.getUserId(),albumId,null);
        return "redirect:/albums/"+albumId;
    }

    @GetMapping("/addComment/{albumId}/{firstCommentId}")
    public String addAlbumCommentReply(@PathVariable Integer albumId, @PathVariable(required = false) Integer firstCommentId,
                                  @RequestParam String text,HttpServletRequest req){
        Users user = (Users) req.getSession().getAttribute("user");
        commentsService.createNewComment(text,firstCommentId,user.getUserId(),albumId,null);
        return "redirect:/albums/"+albumId;
    }

    @GetMapping("/verify/{albumId}")
    public String verifyAlbum(@PathVariable Integer albumId){
        albumService.verifyAlbum(albumId);
        return "redirect:/albums/"+albumId;
    }

    @GetMapping("/unverify/{albumId}")
    public String unverifyAlbum(@PathVariable Integer albumId){
        albumService.unverifyAlbum(albumId);
        return "redirect:/albums/"+albumId;
    }

}
