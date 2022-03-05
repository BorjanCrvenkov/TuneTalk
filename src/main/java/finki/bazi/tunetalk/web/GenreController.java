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
    public String getGenreListPage(@RequestParam(required = false) String genreSearch, Model model) {
        model.addAttribute("genres", genreService.findAllFiltered(genreSearch));

        model.addAttribute("bodyContent", "list-genres");
        return "master-template";
    }

    @GetMapping("/{id}")
    public String getGenrePage(Model model,
            @PathVariable Integer id) {
        Genre genre = genreService.findGenreById(id);
        model.addAttribute("genre", genre);

        model.addAttribute("songsInGenre", songService.findSongsByGenreId(id));

        model.addAttribute("albumsInGenre", albumService.findAlbumsByGenreId(id));

        model.addAttribute("bodyContent", "genre-page");
        return "master-template";
    }

    @GetMapping("/edit/{id}")
    public String getGenreEditPage(Model model, @PathVariable Integer id) {
        Genre genre = genreService.findGenreById(id);
        model.addAttribute("genre", genre);

        model.addAttribute("bodyContent", "edit-genre");
        return "master-template";
    }

    @PostMapping("/edit/{id}")
    public String postGenreEditPage(@PathVariable Integer id,
            @RequestParam String genreName) {
        genreService.updateGenre(id, genreName);
        return "redirect:/genres";
    }

    @GetMapping("/create")
    public String getCreateNewGenrePage(Model model) {
        model.addAttribute("bodyContent", "create-genre");
        return "master-template";
    }

    @PostMapping("/create")
    public String createNewGenre(@RequestParam String genreName, Model model) {

        try {
            genreService.createNewGenre(genreName);
            return "redirect:/genres";
        } catch (Exception ex) {
            model.addAttribute("error", ex.getMessage());
            model.addAttribute("bodyContent", "create-genre");
            return "master-template";
        }

    }

}
