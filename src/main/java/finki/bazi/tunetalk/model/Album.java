package finki.bazi.tunetalk.model;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
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

    public Album(String albumName, LocalDate dateReleased, float rating, boolean verified) {
        this.albumName = albumName;
        this.dateReleased = dateReleased;
        this.rating = rating;
        this.verified = verified;
    }

    public Album() {

    }

    @Override
    public String toString() {
        return "Album{}";
    }

    public Integer getAlbumId() {
        return albumId;
    }

    public void setAlbumId(Integer albumId) {
        this.albumId = albumId;
    }

    public String getAlbumName() {
        return albumName;
    }

    public void setAlbumName(String albumName) {
        this.albumName = albumName;
    }

    public LocalDate getDateReleased() {
        return dateReleased;
    }

    public void setDateReleased(LocalDate dateReleased) {
        this.dateReleased = dateReleased;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public boolean isVerified() {
        return verified;
    }

    public void setVerified(boolean verified) {
        this.verified = verified;
    }

}
