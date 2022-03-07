package finki.bazi.tunetalk.service.impl;

import finki.bazi.tunetalk.model.Album;
import finki.bazi.tunetalk.model.Comment;
import finki.bazi.tunetalk.model.Song;
import finki.bazi.tunetalk.model.Users;
import finki.bazi.tunetalk.repository.CommentRepository;
import finki.bazi.tunetalk.service.AlbumService;
import finki.bazi.tunetalk.service.CommentsService;
import finki.bazi.tunetalk.service.SongService;
import finki.bazi.tunetalk.service.UserService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class CommentsServiceImpl implements CommentsService {

    private final CommentRepository commentRepository;
    private final UserService userService;
    private final SongService songService;
    private final AlbumService albumService;

    public CommentsServiceImpl(CommentRepository commentRepository, UserService userService, SongService songService, AlbumService albumService) {
        this.commentRepository = commentRepository;
        this.userService = userService;
        this.songService = songService;
        this.albumService = albumService;
    }

    @Override
    public Comment findCommentById(Integer commentId) {
        return commentRepository.findById(commentId).get();
    }

    @Override
    public Comment createNewComment(String text, Integer firstCommentId, Integer userId, Integer albumId,
            Integer songId) {
        Users user = this.userService.findByUserId(userId);
        Album album = this.albumService.findById(albumId);
        Song song = this.songService.findById(songId);
        Comment firstComment = this.findCommentById(firstCommentId);

        Comment comment = new Comment(text, LocalDateTime.now(), firstComment, user, album, song);
        return commentRepository.save(comment);
    }

}
