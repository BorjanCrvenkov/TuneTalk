package finki.bazi.tunetalk.service;

import finki.bazi.tunetalk.model.Users;

import java.time.LocalDate;

public interface UserService {

    Users findByUserId(Integer userId);

    Users getUserByUsernameAndPassword(String username, String password);

    Users createNewUser(String username, String password,  String repeatedPassword, String fullName,
                        String email, String mobilePhone, LocalDate birthday,
                        String aboutMe, String userImage);

    Users logIn(String username,String password);

    Users findUserByCommentId(Integer commentId);

    void likeComment(Integer userid,Integer commentId);

    void dislikeComment(Integer userid,Integer commentId);
}