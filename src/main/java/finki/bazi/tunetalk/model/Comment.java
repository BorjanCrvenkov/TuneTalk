package finki.bazi.tunetalk.model;

import finki.bazi.tunetalk.model.enumerations.CommentType;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Entity
//@Table(name="comment", schema = "public")
public class Comment implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer commentId;

    @DateTimeFormat(pattern = "dd-M-yyyy hh:mm:ss")
    private LocalDateTime datePosted;

    private String text;

    private Integer firstCommentId;

    private Integer userId;

    private Integer albumId;

    private Integer songId;

    private CommentType commentType;


    public Comment(LocalDateTime datePosted, String text, Integer firstCommentId, Integer userId, Integer albumId, Integer songId, CommentType commentType) {
        this.datePosted = datePosted;
        this.text = text;
        this.firstCommentId = firstCommentId;
        this.userId = userId;
        this.albumId = albumId;
        this.songId = songId;
        this.commentType = commentType;
    }

    public Comment() {

    }
}
