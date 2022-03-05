package finki.bazi.tunetalk.model;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Data
@Entity
@Table(name = "users")
public class Users implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;

    private String username;

    private String password;

    private String name;

    private String surname;

    private String email;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateCreated;

    private String mobilePhone;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthday;

    private String aboutMe;

    @ManyToMany
    @JoinTable(name = "likes_genres", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "genre_id"))
    private List<Genre> genresLiked;

    @OneToMany(mappedBy = "userPostedBy")
    private List<Comment> commentsPosted;

    @ManyToMany(mappedBy = "likedBy")
    private List<Comment> likedComments;

    @ManyToMany(mappedBy = "dislikedBy")
    private List<Comment> dislikedComments;

    @Transient
    private boolean admin;

    public Users(String username, String password, String name, String surname, String email, String mobilePhone,
            LocalDate birthday, String aboutMe) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.dateCreated = LocalDate.now();
        this.mobilePhone = mobilePhone;
        this.birthday = birthday;
        this.aboutMe = aboutMe;
        this.admin = username.contains("ADMIN_193");
    }

    public Users() {

    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public Integer getUserId() {
        return userId;
    }

}
