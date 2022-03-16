package finki.bazi.tunetalk.model;

import lombok.Data;
import org.h2.engine.User;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Data
@Entity
@Table(name="artist")
public class Artist implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer artistId;

    private String artistName;

    private String realName;

    private String description;

    private boolean verified;

    private String nationality;

    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private LocalDate birthday;

    @Column(name = "artist_image")
    private String artistImage;

    @ManyToMany(mappedBy = "artistReleasedBy")
    private List<Album> albumsReleased;

    @ManyToMany(mappedBy = "artistsReleasedBy")
    private List<Song> songsReleased;

    @ManyToMany
    @JoinTable(
            name = "likes_artist",
            joinColumns = @JoinColumn(name = "artist_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private List<Users> usersLikedBy;

    @ManyToMany
    @JoinTable(
            name = "dislikes_artist",
            joinColumns = @JoinColumn(name = "artist_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private List<Users> usersDislikedBy;

    @Transient
    private List<String> alternativeNames;

    public Artist(String artistName, String realName, String description, LocalDate birthday, String nationality, String artistImage) {
        this.artistName = artistName;
        this.realName = realName;
        this.description = description;
        this.verified = false;
        this.nationality = nationality;
        this.birthday = birthday;
        this.artistImage = artistImage;
        this.albumsReleased = new ArrayList<>();
        this.songsReleased = new ArrayList<>();
        this.usersLikedBy = new ArrayList<>();
        this.usersDislikedBy = new ArrayList<>();
        this.alternativeNames = new ArrayList<>();
    }

    public Artist() {

    }

}