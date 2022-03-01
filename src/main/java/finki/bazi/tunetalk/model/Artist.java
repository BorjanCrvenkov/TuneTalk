package finki.bazi.tunetalk.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Data
@Entity
@Table(name="artist")
public class Artist implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer artistId;

    private String artistName;

    private Integer age;

    private String realName;

    private String description;

    private boolean verified;

    @ManyToMany(mappedBy = "artistReleasedBy")
    private List<Album> albumsReleased;

    @ManyToMany(mappedBy = "artistsReleasedBy")
    private List<Song> songsReleased;

    public Artist(String artistName, String realName, Integer age, String description) {
        this.artistName = artistName;
        this.age = age;
        this.realName = realName;
        this.description = description;
        this.verified = false;
    }

    public Artist() {

    }

    public Integer getArtistId() {
        return artistId;
    }

    public void setArtistId(Integer artistId) {
        this.artistId = artistId;
    }

    public String getArtistName() {
        return artistName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isVerified() {
        return verified;
    }

    public void setVerified(boolean verified) {
        this.verified = verified;
    }

}