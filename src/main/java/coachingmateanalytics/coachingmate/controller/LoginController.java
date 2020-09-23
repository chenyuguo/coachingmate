package coachingmateanalytics.coachingmate.controller;

import coachingmateanalytics.coachingmate.entity.UserPartner;
import coachingmateanalytics.coachingmate.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @Date: 12/9/20 16:12
 * @Description:
 */
@Controller
public class LoginController {

    @Autowired
    UserService userService;

    @RequestMapping("/login")
    public ResponseEntity<UserPartner> login(String username, String password){
        UserPartner userPartner = userService.loginCheck(username, password);
        if (userPartner == null) return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        return new ResponseEntity<>(userPartner, HttpStatus.OK);
    }
}
