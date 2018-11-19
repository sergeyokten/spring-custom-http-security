package oktenweb.springcustomhttpsecurity.controller;

import oktenweb.springcustomhttpsecurity.models.User;
import oktenweb.springcustomhttpsecurity.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {


    @Autowired
    private UserService userService;

    @GetMapping("/save")
    public String save(@RequestParam String username, @RequestParam String password) {
        userService.save(new User(username, password));
        System.out.println(userService.loadUserByUsername(username)+"999999999999999999999");
        return "saved";
    }

    @GetMapping("/user/asd")
    public String userasd() {
        return "userasd";
    }

    @GetMapping("/admin/asd")
    public String adminasd() {
        return "adminasd";
    }
}
