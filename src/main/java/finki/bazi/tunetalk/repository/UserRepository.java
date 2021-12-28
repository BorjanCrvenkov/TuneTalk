package finki.bazi.tunetalk.repository;


import finki.bazi.tunetalk.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<Users, Integer> {

    Users findByUserId(int userId);

    Users findByUsernameAndPassword(String username, String password);

    Users findByUsername(String username);

    Users findByEmail(String email);

}
