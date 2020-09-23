package coachingmateanalytics.coachingmate.service;


import coachingmateanalytics.coachingmate.entity.UserPartner;

/**
 * @Auther: Saul
 * @Date: 23/9/20 16:21
 * @Description:
 */
public interface UserService {
    UserPartner loginCheck(String username, String password);
    UserPartner register(String username, String password);
}
