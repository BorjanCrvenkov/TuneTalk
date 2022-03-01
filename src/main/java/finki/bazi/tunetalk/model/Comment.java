package finki.bazi.tunetalk.model;


import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name="comment")
public class Comment implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer commentId;

    @DateTimeFormat(pattern = "dd-M-yyyy hh:mm:ss")
    private LocalDate datePosted;

    private String text;

    @ManyToOne
    @JoinColumn(name = "first_comment_id")
    private Comment firstComment;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users userPostedBy;

    @ManyToOne
    @JoinColumn(name="album_id")
    private Album albumCommented;

    @ManyToOne
    @JoinColumn(name="song_id")
    private Song songCommented;

    @OneToMany(mappedBy = "firstComment")
    private List<Comment> replies;

    @ManyToMany
    @JoinTable(
            name = "likes",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "comment_id"))
    private List<Users> likedBy;

    @ManyToMany
    @JoinTable(
            name = "likes",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "comment_id"))
    private List<Users> dislikedBy;

    public Comment(String text,LocalDate datePosted, Integer firstCommentId, Integer userId, Integer albumId, Integer songId) {
        this.datePosted = datePosted;
        this.text = text;
//        this.firstCommentId = firstCommentId;
        this.replies = new ArrayList<>();
    }

    public Comment() {

    }


}
