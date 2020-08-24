package coachingmateanalytics.coachingmate.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Auther: Saul
 * @Date: 24/8/20 13:11
 * @Description:
 */
@RestController
public class LoginController {

    @GetMapping
    public String login(){
        return "main page";
    }
}
