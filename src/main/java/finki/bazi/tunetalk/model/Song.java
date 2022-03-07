package finki.bazi.tunetalk.model;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name="song")
public class Song implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer songId;

    private String title;

    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private LocalDate dateReleased;

    private float rating;

    private String lyrics;

    private boolean verified;

    @JoinColumn(name = "song_image")
    private String songImage;

    @ManyToMany
    @JoinTable(
            name = "song_released",
            joinColumns = @JoinColumn(name = "artist_id"),
            inverseJoinColumns = @JoinColumn(name = "song_id"))
    private List<Artist> artistsReleasedBy;

    @ManyToMany(mappedBy = "songsInAlbum")
    private List<Album> albumsIn;

    @ManyToMany
    @JoinTable(
            name = "song_genre",
            joinColumns = @JoinColumn(name = "song_id"),
            inverseJoinColumns = @JoinColumn(name = "genre_id"))
    private List<Genre> songGenres;

    @OneToMany(mappedBy = "songCommented")
    private List<Comment> comments;

    public Song(String title, LocalDate dateReleased, float rating, String lyrics,String songImage) {
        this.title = title;
        this.dateReleased = dateReleased;
        this.rating = rating;
        this.lyrics = lyrics;
        this.verified = false;
        this.songImage = songImage;
        this.artistsReleasedBy = new ArrayList<>();
        this.albumsIn = new ArrayList<>();
        this.songGenres = new ArrayList<>();
        this.comments = new ArrayList<>();
    }

    public Song() {

    }


}
