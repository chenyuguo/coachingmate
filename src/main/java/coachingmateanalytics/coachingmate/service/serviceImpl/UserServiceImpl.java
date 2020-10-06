package coachingmateanalytics.coachingmate.service.serviceImpl;

import coachingmateanalytics.coachingmate.dao.UserDao;
import coachingmateanalytics.coachingmate.entity.UserPartner;
import coachingmateanalytics.coachingmate.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;

/**
 * @Date: 24/9/20 00:50
 * @Description:
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserDao userDao;

    @Override
    public UserPartner loginCheck(String username, String password) {
        UserPartner userPartner = userDao.findUserByUsername(username);
        if(null == userPartner || !userPartner.getPassword().equals(password)) return null;
        return userPartner;
    }

    @Override
    public UserPartner register(String username, String password) {
        UserPartner userPartner = userDao.findUserByUsername(username);
        if(userPartner != null) return null;
        Random random = new Random(901920392323l);
        UserPartner newUserPartner = new UserPartner();
        newUserPartner.setUsername(username);
        newUserPartner.setPassword(password);
        newUserPartner.setUserId(random.nextLong());
        userDao.saveUser(newUserPartner);
        return newUserPartner;
    }
}
