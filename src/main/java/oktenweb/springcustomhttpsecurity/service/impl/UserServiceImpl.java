package oktenweb.springcustomhttpsecurity.service.impl;

import oktenweb.springcustomhttpsecurity.dao.UserDAO;
import oktenweb.springcustomhttpsecurity.models.User;
import oktenweb.springcustomhttpsecurity.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserDAO userDAO;

    @Override
    public void save(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userDAO.save(user);
    }

    @Override
    public List<User> findAll() {
        return userDAO.findAll();
    }

    @Override
    public User findOneById(Integer id) {
        return userDAO.findById(id).get();
    }

    @Override
    public void delete(Integer id) {
        userDAO.deleteById(id);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User byUsername = userDAO.findByUsername(username);
        System.out.println(byUsername+"!!!!!!!!!!!");
        return byUsername;
    }
}
