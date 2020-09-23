package coachingmateanalytics.coachingmate.dao;


import coachingmateanalytics.coachingmate.entity.UserPartner;

public interface UserDao  {

     void saveUser(UserPartner user);
     void saveActivityFile(String activity);
     UserPartner findUserByUserName(String username);
     int updateUser(UserPartner user);
     void deleteUserById(Long id);

}