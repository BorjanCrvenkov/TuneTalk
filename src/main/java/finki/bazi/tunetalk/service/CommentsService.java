package finki.bazi.tunetalk.service;

import finki.bazi.tunetalk.model.Comment;
import finki.bazi.tunetalk.model.Song;
import finki.bazi.tunetalk.model.Users;

import java.util.List;
import java.util.Map;

public interface CommentsService {

    Comment findCommentById(Integer commentId);

    List<Comment> findAllCommentRepliesByCommentId(Integer commentId);

    List<Comment> findAllMainComments();

    List<Comment> findCommentsByAlbumId(Integer albumId);


    List<Comment> findCommentsBySongId(Integer songId);

    void createNewComment(String text,Integer firstCommentId,Integer userId,Integer albumId,Integer songId);

}
