package finki.bazi.tunetalk.web;


import finki.bazi.tunetalk.model.Album;
import finki.bazi.tunetalk.model.Artist;
import finki.bazi.tunetalk.model.Song;
import finki.bazi.tunetalk.model.User;
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
@RequestMapping("/albums")
public class AlbumsController {

    private final AlbumService albumService;
    private final ArtistService artistService;
    private final SongService songService;
    private final GenreService genreService;

    public AlbumsController(AlbumService albumService, ArtistService artistService, SongService songService, GenreService genreService) {
        this.albumService = albumService;
        this.artistService = artistService;
        this.songService = songService;
        this.genreService = genreService;
    }

    @GetMapping
    public String getAllAlbumsPage(Model model, HttpServletRequest req){
        User user = (User) req.getSession().getAttribute("user");
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

        albumService.addNewAlbum(albumName,dateReleased,rating,artistId);

        return "redirect:/home";
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
        return "redirect:/albums";
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
    public String addArtist(@PathVariable int id,Model model){
        Album album = albumService.findById(id);
        model.addAttribute("album",album);

        model.addAttribute("artistsList", artistService.findAll());

        model.addAttribute("bodyContent", "addArtistToAlbum");
        return "master-template";
    }

    @PostMapping("/addArtist/{id}")
    public String addArtistPost(@PathVariable int id,
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

}
