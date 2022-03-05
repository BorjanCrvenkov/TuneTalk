package finki.bazi.tunetalk.web;

import finki.bazi.tunetalk.model.Album;
import finki.bazi.tunetalk.model.Artist;
import finki.bazi.tunetalk.model.Song;
import finki.bazi.tunetalk.model.Users;
import finki.bazi.tunetalk.service.AlbumService;
import finki.bazi.tunetalk.service.ArtistService;
import finki.bazi.tunetalk.service.GenreService;
import finki.bazi.tunetalk.service.SongService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
@RequestMapping("/artists")
public class ArtistsController {

    private final ArtistService artistService;
    private final AlbumService albumService;
    private final SongService songService;
    private final GenreService genreService;

    public ArtistsController(ArtistService artistService, AlbumService albumService, SongService songService,
            GenreService genreService) {
        this.artistService = artistService;
        this.albumService = albumService;
        this.songService = songService;
        this.genreService = genreService;
    }

    @GetMapping
    public String getAllArtistsPage(@RequestParam(required = false) String artistSearch,
            @RequestParam(required = false) String sort, @RequestParam(required = false) String genre,
            @RequestParam(required = false) Integer artistAge, Model model,
            HttpServletRequest req) {
        Users user = (Users) req.getSession().getAttribute("user");
        model.addAttribute("user", user);

        model.addAttribute("artistsList", artistService.findAllFiltered(artistSearch, artistAge, genre));

        model.addAttribute("genres", genreService.listAllGenres());

        List<Integer> ages = IntStream.rangeClosed(1, 125).boxed().collect(Collectors.toList());
        model.addAttribute("ages", ages);
        model.addAttribute("bodyContent", "list-artists");
        return "master-template";
    }

    @GetMapping("/create")
    public String getCreateNewArtistPage(Model model) {
        model.addAttribute("bodyContent", "create-artist");
        return "master-template";
    }

    @PostMapping("/create")
    public String createNewArtist(@RequestParam String artistName,
            @RequestParam(required = false) String realName,
            @RequestParam(required = false) Integer age,
            @RequestParam(required = false) String description) {

        artistService.createNewArtist(artistName, realName, age, description);
        return "redirect:/artists";
    }

    @GetMapping("/{id}")
    public String getArtistPage(Model model, @PathVariable int id) {
        Artist artist = this.artistService.findArtistById(id);
        if (artist == null) {
            return "redirect:/home";
        }
        model.addAttribute("artist", artist);

        List<Album> albums = albumService.findAllAlbumsByArtistId(id);
        model.addAttribute("albums", albums);

        List<Song> songs = songService.findAllSongsByArtistId(id);
        model.addAttribute("songs", songs);

        model.addAttribute("bodyContent", "artist-page");
        return "master-template";
    }

    @GetMapping("/edit/{id}")
    public String getArtistEditPage(Model model, @PathVariable int id) {
        Artist artist = this.artistService.findArtistById(id);
        model.addAttribute("artist", artist);
        model.addAttribute("bodyContent", "edit-artist");
        return "master-template";
    }

    @PostMapping("/edit/{id}")
    public String ArtistEditPage(@PathVariable Integer id,
            @RequestParam String artistName,
            @RequestParam(required = false) String realName,
            @RequestParam(required = false) Integer age,
            @RequestParam(required = false) String description) {

        artistService.updateArtist(id, artistName, realName, age, description);

        return "redirect:/artists/" + id;
    }

    @PostMapping("/search")
    public String artistSearch(@RequestParam int artistId) {
        Artist artist = this.artistService.findArtistById(artistId);
        if (artist != null) {
            return "redirect:/artists/" + artistId;
        } else {
            return "redirect:/artists";
        }

    }

    @GetMapping("/verify/{artistId}")
    public String verifyArtist(@PathVariable Integer artistId) {
        artistService.verifyArtist(artistId);
        return "redirect:/artists/" + artistId;
    }

    @GetMapping("/unverify/{artistId}")
    public String unverifyArtistm(@PathVariable Integer artistId) {
        artistService.unverifyArtist(artistId);
        return "redirect:/artists/" + artistId;
    }

}
