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
//@Table(name="comment", schema = "public")
public class Comment implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer commentId;

    @DateTimeFormat(pattern = "dd-M-yyyy hh:mm:ss")
    private LocalDate datePosted;

    private String text;

    private Integer firstCommentId;

    private Integer userId;

    private Integer albumId;

    private Integer songId;


    @ElementCollection
    private List<Comment> replies;

    @Transient
    private Users user;

    public Comment(String text,LocalDate datePosted, Integer firstCommentId, Integer userId, Integer albumId, Integer songId) {
        this.datePosted = datePosted;
        this.text = text;
        this.firstCommentId = firstCommentId;
        this.userId = userId;
        this.albumId = albumId;
        this.songId = songId;
        this.replies = new ArrayList<>();
    }

    public Comment() {

    }

    public Integer getCommentId() {
        return commentId;
    }

    public void setCommentId(Integer commentId) {
        this.commentId = commentId;
    }

    public LocalDate getDatePosted() {
        return datePosted;
    }

    public void setDatePosted(LocalDate datePosted) {
        this.datePosted = datePosted;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Integer getFirstCommentId() {
        return firstCommentId;
    }

    public void setFirstCommentId(Integer firstCommentId) {
        this.firstCommentId = firstCommentId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getAlbumId() {
        return albumId;
    }

    public void setAlbumId(Integer albumId) {
        this.albumId = albumId;
    }

    public Integer getSongId() {
        return songId;
    }

    public void setSongId(Integer songId) {
        this.songId = songId;
    }


    public List<Comment> getReplies() {
        return replies;
    }

    public void setReplies(List<Comment> replies) {
        this.replies = replies;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }
}
