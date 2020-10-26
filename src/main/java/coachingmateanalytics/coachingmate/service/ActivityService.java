package coachingmateanalytics.coachingmate.service;

import coachingmateanalytics.coachingmate.entity.Statistic;

import java.util.List;

/**
 * @Date: 24/9/20 16:03
 * @Description:
 */
public interface ActivityService {
    void saveActivity(Statistic activity);
    List<Statistic> findAllByUsername(String username);
    List<Statistic> findAllByAccessToken(String accessToken);
}
