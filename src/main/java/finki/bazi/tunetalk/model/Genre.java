package finki.bazi.tunetalk.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Data
@Entity
@Table(name="genre")
public class Genre implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer genreId;

    private String genreName;

    @ManyToMany(mappedBy = "songGenres")
    private List<Song> songs;

    @ManyToMany(mappedBy = "albumGenres")
    private List<Album> albums;

    @ManyToMany(mappedBy = "genresLiked")
    private List<Users> users;

    public Genre(String genreName) {
        this.genreName = genreName;
    }

    public Genre() {

    }

}
