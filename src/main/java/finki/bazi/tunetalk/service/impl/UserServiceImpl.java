package finki.bazi.tunetalk.service.impl;

import finki.bazi.tunetalk.model.Users;
import finki.bazi.tunetalk.model.exceptions.*;
import finki.bazi.tunetalk.repository.UserRepository;
import finki.bazi.tunetalk.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
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


    @Override
    public void createNewUser(String name, String surname, int age, String email, String mobilePhone, String username, String password, String repeatedPassword, String aboutUser) {
        if(checkIfUsernameExists(username)){
            throw new UsernameAlreadyExistsException(username);
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

        Users user = new Users(username, password, name, surname, email, mobilePhone, age, aboutUser);
        userRepository.save(user);
    }

    @Override
    public Users logIn(String username, String password) {
        Users user = this.getUserByUsernameAndPassword(username, password);

        if(user == null){
            throw new UserWithCredentialsDoesNotExistsException(username,password);
        }

        return user;
    }
}
