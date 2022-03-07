package finki.bazi.tunetalk.model;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

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

    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private LocalDate birthday;

    @JoinColumn(name = "artist_image")
    private String artistImage;

    @ManyToMany(mappedBy = "artistReleasedBy")
    private List<Album> albumsReleased;

    @ManyToMany(mappedBy = "artistsReleasedBy")
    private List<Song> songsReleased;

    public Artist(String artistName, String realName, String description, LocalDate birthday, String artistImage) {
        this.artistName = artistName;
        this.realName = realName;
        this.description = description;
        this.birthday = birthday;
        this.artistImage = artistImage;
    }

    public Artist() {

    }

}