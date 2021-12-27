package finki.bazi.tunetalk.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
//@Table(name="genre", schema = "public")
public class Genre implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer genreId;

    private String genreName; // moze i enum?

    @ManyToMany
    private List<Album> albums = new ArrayList<>();;

    @ManyToMany
    private List<Song> songs = new ArrayList<>();;

    @ManyToMany
    private List<User> userLikes = new ArrayList<>();;

    public Genre(String genreName, List<Album> albums, List<Song> songs, List<User> userLikes) {
        this.genreName = genreName;
        this.albums = albums;
        this.songs = songs;
        this.userLikes = userLikes;
    }

    public Genre() {

    }

    public Integer getGenreId() {
        return genreId;
    }

    public void setGenreId(Integer genreId) {
        this.genreId = genreId;
    }

    public String getGenreName() {
        return genreName;
    }

    public void setGenreName(String genreName) {
        this.genreName = genreName;
    }

    public List<Album> getAlbums() {
        return albums;
    }

    public void setAlbums(List<Album> albums) {
        this.albums = albums;
    }

    public List<Song> getSongs() {
        return songs;
    }

    public void setSongs(List<Song> songs) {
        this.songs = songs;
    }

    public List<User> getUserLikes() {
        return userLikes;
    }

    public void setUserLikes(List<User> userLikes) {
        this.userLikes = userLikes;
    }
}
