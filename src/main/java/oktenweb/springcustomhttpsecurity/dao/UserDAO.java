package oktenweb.springcustomhttpsecurity.dao;

import oktenweb.springcustomhttpsecurity.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDAO extends JpaRepository<User, Integer> {

    User findByUsername(String username);
}
