package coachingmateanalytics.coachingmate.service;

import coachingmateanalytics.coachingmate.entity.Activity;

import java.util.List;

/**
 * @Date: 24/9/20 16:03
 * @Description:
 */
public interface ActivityService {
    void saveActivity(Activity activity);
    List<Activity> findAllByUsername(String username);
}
