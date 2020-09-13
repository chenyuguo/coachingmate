package coachingmateanalytics.coachingmate.dao;


import coachingmateanalytics.coachingmate.entity.UserEntity;

public interface UserDao  {

    public void saveUser(UserEntity user);

    public UserEntity findUserByUserName(String userName);

    public int updateUser(UserEntity user);

    public void deleteUserById(Long id);

}