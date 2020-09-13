package coachingmateanalytics.coachingmate.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @Auther: Saul
 * @Date: 12/9/20 16:12
 * @Description:
 */
@RestController
public class LoginController {
    @RequestMapping("/login")
    public ResponseEntity<Object> login(String username, String password){
        //@todo do something for identity check
        Map<String, String> body = new HashMap<>();
        body.put("username",username);
        return new ResponseEntity<>(body, HttpStatus.OK);
    }
}
