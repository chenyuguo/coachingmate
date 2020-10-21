package coachingmateanalytics.coachingmate.dao;

import coachingmateanalytics.coachingmate.entity.Statistic;

import java.util.List;

/**
 * @Date: 24/9/20 10:14
 * @Description:
 */
public interface ActivityDao {
    void saveActivity(Statistic activity);
    List<Statistic> findAllByAccessToken(String accessToken);
}
