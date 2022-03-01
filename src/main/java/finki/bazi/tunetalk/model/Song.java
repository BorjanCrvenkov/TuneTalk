package finki.bazi.tunetalk.model;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Data
@Entity
@Table(name="song")
public class Song implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer songId;

    private String title;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateReleased;

    private float rating;

    private String lyrics;

    private boolean verified;

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

    public Song(String title, LocalDate dateReleased, float rating, String lyrics, boolean verified) {
        this.title = title;
        this.dateReleased = dateReleased;
        this.rating = rating;
        this.lyrics = lyrics;
        this.verified = verified;
    }

    public Song() {

    }

    @Override
    public String toString() {
        return "Song{}";
    }

    public Integer getSongId() {
        return songId;
    }

    public void setSongId(Integer songId) {
        this.songId = songId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public String getLyrics() {
        return lyrics;
    }

    public void setLyrics(String lyrics) {
        this.lyrics = lyrics;
    }

    public boolean isVerified() {
        return verified;
    }

    public void setVerified(boolean verified) {
        this.verified = verified;
    }

}
