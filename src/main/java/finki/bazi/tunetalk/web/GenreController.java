package finki.bazi.tunetalk.web;


import finki.bazi.tunetalk.model.*;
import finki.bazi.tunetalk.service.AlbumService;
import finki.bazi.tunetalk.service.GenreService;
import finki.bazi.tunetalk.service.SongService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/genres")
public class GenreController {

    private final GenreService genreService;
    private final AlbumService albumService;
    private final SongService songService;

    public GenreController(GenreService genreService, AlbumService albumService, SongService songService) {
        this.genreService = genreService;
        this.albumService = albumService;
        this.songService = songService;
    }

    @GetMapping
    public String getGenreListPage(Model model){
        model.addAttribute("genres",genreService.listAllGenres());
        model.addAttribute("bodyContent", "list-genres");
        return "master-template";
    }

    @GetMapping("/{id}")
    public String getGenrePage(Model model, @PathVariable Integer id){
        Genre genre = genreService.findGenreById(id);
        model.addAttribute("genre",genre);

        model.addAttribute("songsInGenre",songService.findAllSongs());

        model.addAttribute("albumsInGenre",albumService.findAllAlbums());

        model.addAttribute("bodyContent", "genre-page");
        return "master-template";
    }

}
