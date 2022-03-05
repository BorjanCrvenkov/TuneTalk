package finki.bazi.tunetalk.service.impl;

import finki.bazi.tunetalk.model.Comment;
import finki.bazi.tunetalk.repository.CommentRepository;
import finki.bazi.tunetalk.service.CommentsService;
import finki.bazi.tunetalk.service.UserService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

@Service
public class CommentsServiceImpl implements CommentsService {

    private final CommentRepository commentRepository;
    private final UserService userService;

    public CommentsServiceImpl(CommentRepository commentRepository, UserService userService) {
        this.commentRepository = commentRepository;
        this.userService = userService;
    }

    @Override
    public Comment findCommentById(Integer commentId) {
        return commentRepository.findById(commentId).get();
    }

    @Override
    public Comment createNewComment(String text, Integer firstCommentId, Integer userId, Integer albumId,
            Integer songId) {
        Comment comment = new Comment(text, LocalDate.now(), firstCommentId, userId, albumId, songId);
        return commentRepository.save(comment);
    }

}
