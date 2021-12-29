package finki.bazi.tunetalk.service.impl;

import finki.bazi.tunetalk.model.Comment;
import finki.bazi.tunetalk.model.Song;
import finki.bazi.tunetalk.model.Users;
import finki.bazi.tunetalk.repository.CommentRepository;
import finki.bazi.tunetalk.service.CommentsService;
import finki.bazi.tunetalk.service.UserService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
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
        return commentRepository.findByCommentId(commentId);
    }

    @Override
    public List<Comment> findAllCommentRepliesByCommentId(Integer commentId) {
        List<Integer> commentIds = commentRepository.findAllCommentRepliesByCommentId(commentId);
        List<Comment> comments = new ArrayList<>();

        for(Integer id : commentIds){
            comments.add(this.findCommentById(id));
        }

        return comments;
    }

    @Override
    public List<Comment> findAllMainComments() {
        List<Integer> commentIds = commentRepository.findAllMainComments();
        List<Comment> comments = new ArrayList<>();

        for(Integer id : commentIds){
            comments.add(this.findCommentById(id));
        }

        return comments;
    }

    @Override
    public Map<Comment, Users> commentsAndUsers(List<Comment> comments) {
        Map<Comment, Users> commentUsersMap = new HashMap<>();

        for(Comment c : comments){
            Integer userId = commentRepository.findUserByCommentId(c.getCommentId());
            Users user = userService.findByUserId(userId);
            commentUsersMap.put(c,user);
        }

        return commentUsersMap;

    }



    private void rekurzija(Integer id){
        List<Comment> replies = findAllCommentRepliesByCommentId(id);

        for(Comment r : replies){
            rekurzija(r.getCommentId());
        }

        Comment comment = findCommentById(id);
        comment.setReplies(replies);
    }

    @Override
    public List<Comment> findCommentsByAlbumId(Integer albumId) {
        List<Integer> commentIds = commentRepository.findAllCommentsByAlbumId(albumId);
        List<Comment> comments = new ArrayList<>();

        for(Integer id : commentIds){
            Comment comment = this.findCommentById(id);
            rekurzija(id);
            comments.add(comment);
        }

        return comments;
    }

    @Override
    public List<Comment> findCommentsBySongId(Integer songId) {
        List<Integer> commentIds = commentRepository.findAllCommentsBySongId(songId);
        List<Comment> comments = new ArrayList<>();

        for(Integer id : commentIds){
            Comment comment = this.findCommentById(id);
            rekurzija(id);
            comments.add(comment);
        }

        return comments;
    }

    @Override
    public void createNewComment(String text, Integer firstCommentId, Integer userId, Integer albumId, Integer songId) {
        Comment comment = new Comment(text, LocalDate.now(),firstCommentId,userId,albumId,songId);
        commentRepository.save(comment);
    }


}
