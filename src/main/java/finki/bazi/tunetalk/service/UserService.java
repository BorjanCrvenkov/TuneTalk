package finki.bazi.tunetalk.service;

import finki.bazi.tunetalk.model.Users;

public interface UserService {

    Users getUserByUsernameAndPassword(String username, String password);

    void createNewUser(String name, String surname, int age, String email,
                       String mobilePhone, String username, String password,
                       String repeatedPassword, String aboutUser);
}