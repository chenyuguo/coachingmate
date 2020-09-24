package coachingmateanalytics.coachingmate.service.serviceImpl;

import coachingmateanalytics.coachingmate.dao.ActivityDao;
import coachingmateanalytics.coachingmate.dao.UserDao;
import coachingmateanalytics.coachingmate.entity.Activity;
import coachingmateanalytics.coachingmate.entity.UserPartner;
import coachingmateanalytics.coachingmate.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Date: 24/9/20 16:05
 * @Description:
 */
@Service
public class ActivityServiceImpl implements ActivityService {

    @Autowired
    ActivityDao activityDao;

    @Autowired
    UserDao userDao;

    @Override
    public void saveActivity(Activity activity) {
        activityDao.saveActivity(activity);
    }

    @Override
    public List<Activity> findAllByUsername(String username) {
        UserPartner user = userDao.findUserByUsername(username);
        return activityDao.findAllByAccessToken(user.getUserAccessToken());
    }
}
