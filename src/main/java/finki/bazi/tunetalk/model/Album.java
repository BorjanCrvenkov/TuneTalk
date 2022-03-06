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
@Table(name="album")
public class Album implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer albumId;

    private String albumName;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateReleased;

    private float rating;

    private boolean verified;

    @JoinColumn(name = "album_image")
    private String albumImage;

    @ManyToMany
    @JoinTable(
            name = "album_released",
            joinColumns = @JoinColumn(name = "artist_id"),
            inverseJoinColumns = @JoinColumn(name = "album_id"))
    private List<Artist> artistReleasedBy;

    @ManyToMany
    @JoinTable(
            name = "is_in",
            joinColumns = @JoinColumn(name = "song_id"),
            inverseJoinColumns = @JoinColumn(name = "album_id"))
    private List<Song> songsInAlbum;

    @ManyToMany
    @JoinTable(
            name = "album_genre",
            joinColumns = @JoinColumn(name = "album_id"),
            inverseJoinColumns = @JoinColumn(name = "genre_id"))
    private List<Genre> albumGenres;

    @OneToMany(mappedBy = "albumCommented")
    private List<Comment> comments;

    public Album(String albumName, LocalDate dateReleased, float rating, String albumImage) {
        this.albumName = albumName;
        this.dateReleased = dateReleased;
        this.rating = rating;
        this.verified = false;
        this.albumImage = albumImage;
        this.artistReleasedBy = new ArrayList<>();
        this.songsInAlbum = new ArrayList<>();
        this.albumGenres = new ArrayList<>();
        this.comments = new ArrayList<>();
    }

    public Album() {

    }


}
