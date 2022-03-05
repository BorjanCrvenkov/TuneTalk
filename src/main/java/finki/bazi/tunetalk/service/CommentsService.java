package finki.bazi.tunetalk.service;

import finki.bazi.tunetalk.model.Comment;

import java.util.List;

public interface CommentsService {

    Comment findCommentById(Integer commentId);

    Comment createNewComment(String text, Integer firstCommentId, Integer userId, Integer albumId, Integer songId);

}
