package finki.bazi.tunetalk.repository;


import finki.bazi.tunetalk.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<Users, Integer> {

    Users findByUserId(int userId);

    Users findByUsernameAndPassword(String username, String password);

    Users findByUsername(String username);

    Users findByEmail(String email);

    @Query(value = "select user_id from comment where comment_id = :id",nativeQuery = true)
    Integer findByCommentId(@Param("id") Integer commentId);


    @Query(value = "select COUNT(user_id) from likes where user_id = :userId and comment_id = :commentId",nativeQuery = true)
    Integer checkIfUserLikesComment(@Param("userId") Integer userId,@Param("commentId") Integer commentId);


    @Query(value = "select COUNT(user_id) from dislikes where user_id = :userId and comment_id = :commentId",nativeQuery = true)
    Integer checkIfUserDislikesComment(@Param("userId") Integer userId,@Param("commentId") Integer commentId);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "delete from likes where user_id = :userId and comment_id = :commentId ",nativeQuery = true)
    void removeUserLikesComment(@Param("userId") Integer userId,@Param("commentId") Integer commentId);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "delete from dislikes where user_id = :userId and comment_id = :commentId ",nativeQuery = true)
    void removeUserDislikesComment(@Param("userId") Integer userId,@Param("commentId") Integer commentId);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "insert into likes(user_id,comment_id) values(:userId,:commentId)",nativeQuery = true)
    void likeComment(@Param("userId") Integer userId,@Param("commentId") Integer commentId);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "insert into dislikes(user_id,comment_id) values(:userId,:commentId)",nativeQuery = true)
    void dislikeComment(@Param("userId") Integer userId,@Param("commentId") Integer commentId);


}
