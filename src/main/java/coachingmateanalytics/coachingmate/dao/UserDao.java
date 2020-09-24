package coachingmateanalytics.coachingmate.dao;


import coachingmateanalytics.coachingmate.entity.UserPartner;

public interface UserDao  {

     void saveUser(UserPartner user);
     UserPartner findUserByUsername(String username);
     int updateUser(UserPartner user);
     void deleteUserByUsername(String username);

}