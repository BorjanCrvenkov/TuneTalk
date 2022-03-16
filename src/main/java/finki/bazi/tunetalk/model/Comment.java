package finki.bazi.tunetalk.model;


import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
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

    @DateTimeFormat(pattern = "dd-MM-yyyy hh:mm:ss")
    private LocalDateTime datePosted;

    private String text;

    @ManyToOne
    @JoinColumn(name = "reply_to_comment_id")
    private Comment replyToComment;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users userPostedBy;

    @ManyToOne
    @JoinColumn(name="album_id")
    private Album albumCommented;

    @ManyToOne
    @JoinColumn(name="song_id")
    private Song songCommented;

    @OneToMany(mappedBy = "replyToComment")
    private List<Comment> replies;

    @ManyToMany
    @JoinTable(
            name = "likes_comment",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "comment_id"))
    private List<Users> usersLikedBy;

    @ManyToMany
    @JoinTable(
            name = "dislikes_comment",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "comment_id"))
    private List<Users> usersDislikedBy;

    public Comment(String text, LocalDateTime datePosted, Comment replyToComment, Users userPostedBy, Album albumCommented, Song songCommented) {
        this.datePosted = datePosted;
        this.text = text;
        this.replyToComment = replyToComment;
        this.userPostedBy = userPostedBy;
        this.albumCommented = albumCommented;
        this.songCommented = songCommented;
        this.replies = new ArrayList<>();
        this.usersLikedBy = new ArrayList<>();
        this.usersDislikedBy = new ArrayList<>();
    }

    public Comment() {

    }


}
