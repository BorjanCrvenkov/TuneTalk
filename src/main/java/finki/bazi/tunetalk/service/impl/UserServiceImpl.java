package finki.bazi.tunetalk.service.impl;

import finki.bazi.tunetalk.model.Users;
import finki.bazi.tunetalk.model.exceptions.*;
import finki.bazi.tunetalk.repository.UserRepository;
import finki.bazi.tunetalk.service.UserService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Users findByUserId(Integer userId) {
        return userRepository.findByUserId(userId);
    }

    @Override
    public Users getUserByUsernameAndPassword(String username, String password) {
        return userRepository.findByUsernameAndPassword(username, password);
    }

    private boolean checkIfUsernameExists(String username){
        return userRepository.findByUsername(username) != null;
    }

    private boolean checkIfEmailExists(String email){
        return userRepository.findByEmail(email) != null;
    }

    private boolean checkIfPasswordsMatch(String password, String repeatedPassword){
        return !password.equals(repeatedPassword);
    }

    private boolean verifyEmail(String email){
        return email.contains("@") && email.contains(".com"); // ako ima @ i .com validen e
    }

    private boolean checkIfUsernameContainsAdmin(String username){
        return username.toLowerCase().contains("admin");
    }


    @Override
    public Users createNewUser(String username, String password,  String repeatedPassword, String fullName,
                               String email, String mobilePhone, LocalDate birthday,
                               String aboutMe, String userImage) {
        if(checkIfUsernameExists(username)){
            throw new UsernameAlreadyExistsException(username);
        }

        if(checkIfUsernameContainsAdmin(username)){
            throw new UsernameCantContainAdminException();
        }

        if(!verifyEmail(email)){
            throw new InvalidEmailException();
        }


        if (checkIfEmailExists(email)){
            throw new EmailAlreadyExistsException(email);
        }

        if(checkIfPasswordsMatch(password, repeatedPassword)){
            throw new PasswordsDoNotMatchException();
        }

        Users user = new Users(username, password, fullName, email,mobilePhone, birthday, aboutMe, userImage);
        return userRepository.save(user);
    }

    private boolean checkIfUserIsAdmin(String username){
        return username.contains("ADMIN_193");
    }

    @Override
    public Users logIn(String username, String password) {
        Users user = this.getUserByUsernameAndPassword(username, password);

        if(user == null){
            throw new UserWithCredentialsDoesNotExistsException();
        }

        user.setAdmin(checkIfUserIsAdmin(username));

        return user;
    }

    @Override
    public Users findUserByCommentId(Integer commentId) {
        Integer id = userRepository.findByCommentId(commentId);
        return this.findByUserId(id);
    }

    @Override
    public void likeComment(Integer userid, Integer commentId) {

        if(this.userRepository.checkIfUserDislikesComment(userid, commentId) != 0){
            this.userRepository.removeUserDislikesComment(userid, commentId);
            this.userRepository.likeComment(userid, commentId);
            // ako e disliked  komentarot izbrisi go dislike-ot i dodadi like

        }else  if(this.userRepository.checkIfUserLikesComment(userid, commentId) != 0){
            this.userRepository.removeUserLikesComment(userid,commentId);
            // ako vekje e like-nat komentarot napravi unlike

        }else{
            this.userRepository.removeUserLikesComment(userid,commentId);
            // like comment
        }



    }

    @Override
    public void dislikeComment(Integer userid, Integer commentId) {

        if(this.userRepository.checkIfUserLikesComment(userid, commentId) != 0){
            this.userRepository.removeUserLikesComment(userid, commentId);
            this.userRepository.dislikeComment(userid, commentId);
            // ako e liked  komentarot izbrisi go like-ot i dodadi dislike

        }else  if(this.userRepository.checkIfUserDislikesComment(userid, commentId) != 0){
            this.userRepository.removeUserLikesComment(userid,commentId);
            // ako vekje e dislike-nat komentarot napravi undislike

        }else{
            this.userRepository.removeUserLikesComment(userid,commentId);
            // dislike comment
        }

    }

}
