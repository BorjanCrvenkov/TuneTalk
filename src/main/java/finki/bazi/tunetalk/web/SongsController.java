package finki.bazi.tunetalk.web;

import finki.bazi.tunetalk.model.*;
import finki.bazi.tunetalk.service.*;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
@RequestMapping("/songs")
public class SongsController {

    private final SongService songService;
    private final AlbumService albumService;
    private final ArtistService artistService;
    private final GenreService genreService;
    private final CommentsService commentsService;

    public SongsController(SongService songService, AlbumService albumService, ArtistService artistService,
            GenreService genreService, CommentsService commentsService) {
        this.songService = songService;
        this.albumService = albumService;
        this.artistService = artistService;
        this.genreService = genreService;
        this.commentsService = commentsService;
    }

    @GetMapping
    public String getAllSongsPage(@RequestParam(required = false) String songSearch,
            @RequestParam(required = false) Integer yearReleased, @RequestParam(required = false) String genre,
            Model model, HttpServletRequest req) {
        Users user = (Users) req.getSession().getAttribute("user");
        model.addAttribute("user", user);

        List<Song> songs = songService.findAllFiltered(songSearch, yearReleased, genre);

        List<Integer> years = IntStream.rangeClosed(1940, Calendar.getInstance().get(Calendar.YEAR)).boxed()
                .collect(Collectors.toList());
        Collections.reverse(years);
        model.addAttribute("years", years);

        model.addAttribute("genres", this.genreService.listAllGenres());

        model.addAttribute("songsList", songs);
        model.addAttribute("bodyContent", "list-songs");
        return "master-template";
    }

    @GetMapping("/create")
    public String getCreateNewSongPage(Model model) {
        model.addAttribute("bodyContent", "create-song");
        return "master-template";
    }

    @PostMapping("/create")
    public String createNewSong(@RequestParam String title,
            @RequestParam("dateReleased") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateReleased,
            @RequestParam float rating,
            @RequestParam String lyrics,
            @RequestParam String songImage) {
        this.songService.createNewSong(title, dateReleased, rating, lyrics, songImage);

        return "redirect:/songs";
    }

    @GetMapping("/{id}")
    public String getSongPage(Model model, @PathVariable int id) {

        model.addAttribute("song", songService.findById(id));

        // model.addAttribute("album",albumService.findAlbumBySongId(id));

        // model.addAttribute("artists",artistService.findArtistsBySongId(id));

        // model.addAttribute("genres", genreService.findGenresBySongId(id));

        List<Integer> years = IntStream.rangeClosed(1940, Calendar.getInstance().get(Calendar.YEAR)).boxed()
                .collect(Collectors.toList());
        Collections.reverse(years);
        model.addAttribute("years", years);

        model.addAttribute("bodyContent", "song-page");

        return "master-template";
    }

    @GetMapping("/edit/{id}")
    public String getSongEditPage(Model model, @PathVariable int id) {
        Song song = songService.findById(id);
        model.addAttribute("song", song);

        model.addAttribute("artistList", artistService.findArtistsBySongId(id));

        model.addAttribute("genres", genreService.findGenresBySongId(id));

        model.addAttribute("bodyContent", "edit-song");
        return "master-template";
    }

    @PostMapping("/edit/{id}")
    public String SongEditPage(@PathVariable Integer id,
            @RequestParam String title,
            @RequestParam("dateReleased") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateReleased,
            @RequestParam float rating,
            @RequestParam(required = false) String lyrics) {

        songService.updateSong(id, title, dateReleased, rating, lyrics);
        return "redirect:/albums/" + id;
    }

    @PostMapping("/search")
    public String songSearch(@RequestParam int songId) {
        Song song = songService.findById(songId);
        if (song != null) {
            return "redirect:/songs/" + songId;
        } else {
            return "redirect:/songs";
        }
    }

    @GetMapping("/addArtist/{id}")
    public String addArtist(@PathVariable int id, Model model) {
        Song song = songService.findById(id);
        model.addAttribute("song", song);

        model.addAttribute("artistsList", artistService.findAll());

        model.addAttribute("bodyContent", "addArtistToSong");
        return "master-template";
    }

    @PostMapping("/addArtist/{id}")
    public String addArtistPost(@PathVariable int id,
            @RequestParam Integer artistId) {

        artistService.addArtistToSong(id, artistId);
        return "redirect:/songs/" + id;
    }

    @GetMapping("/deleteArtistFromSong/{songId}/{artistId}")
    public String deleteArtistInSong(@PathVariable Integer songId, @PathVariable Integer artistId) {
        songService.deleteArtistFromSong(artistId, songId);
        return "redirect:/songs/" + songId;
    }

    @GetMapping("/addGenre/{id}")
    public String addGenre(@PathVariable int id, Model model) {

        model.addAttribute("song", songService.findById(id));

        model.addAttribute("genres", genreService.listAllGenres());

        model.addAttribute("bodyContent", "addGenreToSong");
        return "master-template";
    }

    @PostMapping("/addGenre/{id}")
    public String addGenrePost(@PathVariable int id,
            @RequestParam Integer genreId) {

        songService.addGenreToSong(genreId, id);
        return "redirect:/songs/" + id;
    }

    @GetMapping("/deleteGenreFromSong/{songId}/{genreId}")
    public String deletegenreFromSong(@PathVariable Integer songId, @PathVariable Integer genreId) {
        songService.deleteGenreFromSong(genreId, songId);
        return "redirect:/songs/" + songId;
    }

    @GetMapping("/addComment/{songId}")
    public String addSongComment(@PathVariable Integer songId,
            @RequestParam String text, HttpServletRequest req) {
        Users user = (Users) req.getSession().getAttribute("user");
        commentsService.createNewComment(text, null, user.getUserId(), null, songId);
        return "redirect:/songs/" + songId;
    }

    @GetMapping("/addComment/{songId}/{firstCommentId}")
    public String addSongCommentReply(@PathVariable Integer songId,
            @PathVariable(required = false) Integer firstCommentId,
            @RequestParam String text, HttpServletRequest req) {
        Users user = (Users) req.getSession().getAttribute("user");
        commentsService.createNewComment(text, firstCommentId, user.getUserId(), null, songId);
        return "redirect:/songs/" + songId;
    }

    @GetMapping("/verify/{songId}")
    public String verifySong(@PathVariable Integer songId) {
        songService.verifySong(songId);
        return "redirect:/songs/" + songId;
    }

    @GetMapping("/unverify/{songId}")
    public String unverifySong(@PathVariable Integer songId) {
        songService.unverifySong(songId);
        return "redirect:/songs/" + songId;
    }

}
