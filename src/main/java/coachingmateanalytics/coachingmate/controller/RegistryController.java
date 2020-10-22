package coachingmateanalytics.coachingmate.controller;

import coachingmateanalytics.coachingmate.entity.UserPartner;
import coachingmateanalytics.coachingmate.service.UserService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
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

    @GetMapping("/register")
    @ApiOperation(value = "register interface", notes = "user registration")
    public ResponseEntity<UserPartner> register(@ApiParam(required = true, type = "String")
                                                @RequestParam("username") String username,
                                                @ApiParam(required = true, type = "String")
                                                @RequestParam("password") String password){
        UserPartner user = userService.register(username, password);
        if (user == null) return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
        return ResponseEntity.ok(user);
    }

}
