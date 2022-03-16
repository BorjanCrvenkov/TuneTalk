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
@Table(name = "users", schema = "public")
public class Users implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;

    private String username;

    private String password;

    @Column(name = "fullname")
    private String fullName;

    private String email;

    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private LocalDate dateCreated;

    private String mobilePhone;

    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private LocalDate birthday;

    private String aboutMe;

    @JoinColumn(name = "user_image")
    private String userImage;

    @ManyToMany
    @JoinTable(name = "likes_genres",
            joinColumns = @JoinColumn(name = "user_id")
            , inverseJoinColumns = @JoinColumn(name = "genre_id"))
    private List<Genre> genresLiked;

    @ManyToMany
    @JoinTable(name = "dislikes_genres",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "genre_id"))
    private List<Genre> genresDisliked;

    @OneToMany(mappedBy = "userPostedBy")
    private List<Comment> commentsPosted;

    @ManyToMany(mappedBy = "usersLikedBy")
    private List<Comment> commentsLiked;

    @ManyToMany(mappedBy = "usersLikedBy")
    private List<Comment> commentsDisliked;

    @ManyToMany(mappedBy = "usersLikedBy")
    private List<Artist> artistsLiked;

    @ManyToMany(mappedBy = "usersLikedBy")
    private List<Artist> artistsDisliked;

    @ManyToMany(mappedBy = "usersLikedBy")
    private List<Album> albumsLiked;

    @ManyToMany(mappedBy = "usersLikedBy")
    private List<Album> albumsDisliked;

    @ManyToMany(mappedBy = "usersLikedBy")
    private List<Song> songsLiked;

    @ManyToMany(mappedBy = "usersLikedBy")
    private List<Song> songsDisliked;

    @Transient
    private boolean admin;

    public Users(String username, String password, String fullName, String email,
                 String mobilePhone, LocalDate birthday, String aboutMe, String userImage) {
        this.username = username;
        this.password = password;
        this.fullName = fullName;
        this.email = email;
        this.dateCreated = LocalDate.now();
        this.mobilePhone = mobilePhone;
        this.birthday = birthday;
        this.aboutMe = aboutMe;
        this.admin = username.contains("ADMIN_193");
        this.userImage = userImage;
        this.genresLiked = new ArrayList<>();
        this.genresDisliked = new ArrayList<>();
        this.commentsPosted = new ArrayList<>();
        this.commentsLiked = new ArrayList<>();
        this.commentsDisliked = new ArrayList<>();
        this.artistsLiked = new ArrayList<>();
        this.artistsDisliked = new ArrayList<>();
        this.albumsLiked = new ArrayList<>();
        this.albumsDisliked = new ArrayList<>();
        this.songsLiked = new ArrayList<>();
        this.songsDisliked = new ArrayList<>();
    }

    public Users() {

    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

}
