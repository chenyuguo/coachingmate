package coachingmateanalytics.coachingmate.controller;

import coachingmateanalytics.coachingmate.dao.UserDao;
import coachingmateanalytics.coachingmate.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Auther: Saul
 * @Date: 18/9/20 00:03
 * @Description:
 */
@RestController
public class Storage {

    @Autowired
    UserDao userDao;

    @GetMapping("/store")
    public UserEntity storeUser(String name, String passWord){
        UserEntity userEntity = new UserEntity();
        userEntity.setUserName(name);
        userEntity.setPassWord(passWord);
        userDao.saveUser(userEntity);
        return userEntity;
    }
}
