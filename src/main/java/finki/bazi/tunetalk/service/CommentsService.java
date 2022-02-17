package finki.bazi.tunetalk.service;

import finki.bazi.tunetalk.model.Comment;

import java.util.List;

public interface CommentsService {

    Comment findCommentById(Integer commentId);

    List<Comment> findAllCommentRepliesByCommentId(Integer commentId);

    List<Comment> findAllMainComments();

    List<Comment> findCommentsByAlbumId(Integer albumId);


    List<Comment> findCommentsBySongId(Integer songId);

    Comment createNewComment(String text,Integer firstCommentId,Integer userId,Integer albumId,Integer songId);

}
