package finki.bazi.tunetalk.repository;

import finki.bazi.tunetalk.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer> {

    @Query(value = "select comment_id from comment where first_comment_id = :id",nativeQuery = true)
    List<Integer> findAllCommentRepliesByCommentId(@Param("id") Integer commentId);

    @Query(value = "select comment_id from comment where first_comment_id = null",nativeQuery = true)
    List<Integer> findAllMainComments();

    @Query(value = "select comment_id from comment where album_id = :id",nativeQuery = true)
    List<Integer> findAllCommentsByAlbumId(@Param("id") Integer albumId);

    @Query(value = "select user_id from comment where comment_id = :id",nativeQuery = true)
    Integer findUserByCommentId(@Param("id") Integer commentId);

    @Query(value = "select comment_id from comment where song_id = :id",nativeQuery = true)
    List<Integer> findAllCommentsBySongId(@Param("id") Integer songId);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "insert into comment(date_posted,text,first_comment_id,user_id,album_id,song_id values(:text,:datePosted,:firstCommentId,:userId,:albumId,:songId))",nativeQuery = true)
    void insertNewComment(@Param("text")String text,@Param("datePosted") LocalDate datePosted,
                          @Param("firstCommentId") Integer firstCommentId,@Param("userId")Integer userId,
                          @Param("albumId") Integer albumId,@Param("songId") Integer songId);

}
