package finki.bazi.tunetalk.web;

import finki.bazi.tunetalk.model.*;
import finki.bazi.tunetalk.service.AlbumService;
import finki.bazi.tunetalk.service.ArtistService;
import finki.bazi.tunetalk.service.GenreService;
import finki.bazi.tunetalk.service.SongService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/songs")
public class SongsController {

    private final SongService songService;
    private final AlbumService albumService;
    private final ArtistService artistService;
    private final GenreService genreService;

    public SongsController(SongService songService, AlbumService albumService, ArtistService artistService, GenreService genreService) {
        this.songService = songService;
        this.albumService = albumService;
        this.artistService = artistService;
        this.genreService = genreService;
    }

    @GetMapping
    public String getAllSongsPage(Model model, HttpServletRequest req){
        User user = (User) req.getSession().getAttribute("user");
        model.addAttribute("user",user);

        List<Song> songs = songService.findAllSongs();

        model.addAttribute("songsList", songs);
        model.addAttribute("bodyContent", "list-songs");
        return "master-template";
    }

    @GetMapping("/create")
    public String getCreateNewAlbumPage(Model model){
//        model.addAttribute("artistList", DataHolder.artists);
        model.addAttribute("bodyContent", "create-song");
        return "master-template";
    }

    @PostMapping("/create")
    public String createNewArtist(){
        return "redirect:/home";
    }

    @GetMapping("/{id}")
    public String getSongPage(Model model, @PathVariable int id){

        model.addAttribute("song",songService.findById(id));

        model.addAttribute("album",albumService.findAlbumBySongId(id));

        model.addAttribute("artists",artistService.findArtistsBySongId(id));

        model.addAttribute("genres", genreService.findGenresBySongId(id));

        model.addAttribute("bodyContent", "song-page");

        return "master-template";
    }

    @GetMapping("/edit/{id}")
    public String getSongEditPage(Model model, @PathVariable int id){
        Song song = songService.findById(id);
        model.addAttribute("song",song);

        model.addAttribute("artistList", artistService.findArtistsBySongId(id));

        model.addAttribute("genres", genreService.findGenresBySongId(id));

        model.addAttribute("bodyContent", "edit-song");
        return "master-template";
    }

    @PostMapping("/edit/{id}")
    public String SongEditPage(@RequestParam String title,
                                @RequestParam("dateReleased") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateReleased,
                                @RequestParam float rating,
                                @RequestParam int artistId){


        return "redirect:/albums";
    }

    @PostMapping("/search")
    public String songSearch(@RequestParam int songId){
        Song song = songService.findById(songId);
        if(song != null){
            return "redirect:/songs/"+songId;
        }else{
            return "redirect:/songs";
        }
    }

    @GetMapping("/addArtist/{id}")
    public String addArtist(@PathVariable int id,Model model){
        Song song = songService.findById(id);
        model.addAttribute("song",song);

        model.addAttribute("artistsList", artistService.findAll());

        model.addAttribute("bodyContent", "addArtistToSong");
        return "master-template";
    }

    @PostMapping("/addArtist/{id}")
    public String addArtistPost(@PathVariable int id,
                                @RequestParam Integer artistId){

        artistService.addArtistToSong(id,artistId);
        return "redirect:/home";
    }

    @GetMapping("/deleteArtistFromSong/{songId}/{artistId}")
    public String deleteArtistInSong(@PathVariable Integer songId,@PathVariable Integer artistId){
        songService.deleteArtistFromSong(artistId,songId);
        return "redirect:/songs/"+songId;
    }

    @GetMapping("/addGenre/{id}")
    public String addGenre(@PathVariable int id,Model model){

        model.addAttribute("song",songService.findById(id));

        model.addAttribute("genres", genreService.listAllGenres());

        model.addAttribute("bodyContent", "addGenreToSong");
        return "master-template";
    }

    @PostMapping("/addGenre/{id}")
    public String addGenrePost(@PathVariable int id,
                                @RequestParam Integer genreId){

        songService.addGenreToSong(genreId,id);
        return "redirect:/home";
    }

    @GetMapping("/deleteGenreFromSong/{songId}/{genreId}")
    public String deletegenreFromSong(@PathVariable Integer songId,@PathVariable Integer genreId){
        songService.deleteGenreFromSong(genreId,songId);
        return "redirect:/songs/"+songId;
    }

}
