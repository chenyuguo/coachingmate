package coachingmateanalytics.coachingmate.dao;

import coachingmateanalytics.coachingmate.entity.Activity;

import java.util.List;

/**
 * @Date: 24/9/20 10:14
 * @Description:
 */
public interface ActivityDao {
    void saveActivity(Activity activity);
    List<Activity> findAllByAccessToken(String accessToken);
}
