package coachingmateanalytics.coachingmate.controller;

import coachingmateanalytics.coachingmate.entity.UserPartner;
import coachingmateanalytics.coachingmate.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Auther: Saul
 * @Date: 24/9/20 00:47
 * @Description:
 */
@RestController
public class RegistryController {

    @Autowired
    UserService userService;

    @PostMapping("/register")
    public ResponseEntity<UserPartner> register(@RequestParam("username") String username, @RequestParam("password") String password){
        UserPartner user = userService.register(username, password);
        if (user == null) return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
        return ResponseEntity.ok(user);
    }

}
