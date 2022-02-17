package finki.bazi.tunetalk.service;

import finki.bazi.tunetalk.model.Users;

import java.time.LocalDate;

public interface UserService {

    Users findByUserId(Integer userId);

    Users getUserByUsernameAndPassword(String username, String password);

    Users createNewUser(String name, String surname, LocalDate birthday, String email,
                       String mobilePhone, String username, String password,
                       String repeatedPassword, String aboutUser);

    Users logIn(String username,String password);

    Users findUserByCommentId(Integer commentId);

    void likeComment(Integer userid,Integer commentId);

    void dislikeComment(Integer userid,Integer commentId);
}