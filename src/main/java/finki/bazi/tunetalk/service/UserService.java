package finki.bazi.tunetalk.service;

import finki.bazi.tunetalk.model.Comment;
import finki.bazi.tunetalk.model.Users;

import java.util.List;

public interface UserService {

    Users findByUserId(Integer userId);

    Users getUserByUsernameAndPassword(String username, String password);

    void createNewUser(String name, String surname, int age, String email,
                       String mobilePhone, String username, String password,
                       String repeatedPassword, String aboutUser);

    Users logIn(String username,String password);

    Integer findUserByCommentId(Integer commentId);

    void likeComment(Integer userid,Integer commentId);

    void dislikeComment(Integer userid,Integer commentId);
}