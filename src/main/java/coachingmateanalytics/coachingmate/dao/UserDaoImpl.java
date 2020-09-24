package coachingmateanalytics.coachingmate.dao;


import coachingmateanalytics.coachingmate.entity.UserPartner;
import coachingmateanalytics.coachingmate.utils.Consts;
import com.alibaba.fastjson.JSON;
import com.mongodb.client.result.UpdateResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;


@Component
public class UserDaoImpl implements UserDao {
    @Autowired
    private MongoTemplate mongoTemplate;

    /***
     * @Description test remote mongodb using collection name
     * @Date 23:24 22/9/20
     * @param user
     * @return {@link }
     */
    public void saveUser(UserPartner user) {
        mongoTemplate.save(user, Consts.MONGODB_USER_COLLECTIN_NAME);
    }

    public UserPartner findUserByUsername(String username) {
        Query query=new Query(Criteria.where("username").is(username));
        UserPartner user =  mongoTemplate.findOne(query , UserPartner.class,Consts.MONGODB_USER_COLLECTIN_NAME);
        return user;
    }

    public int updateUser(UserPartner user) {
        Query query=new Query(Criteria.where("username").is(user.getUsername()));
        Update update= Update.update("userAccessToken", user.getUserAccessToken()).set("userAccessSecret", user.getUserAccessSecret());
        UpdateResult result =mongoTemplate.updateMulti(query,update,UserPartner.class,Consts.MONGODB_USER_COLLECTIN_NAME);
        return (int) result.getMatchedCount();
    }

    @Override
    public void deleteUserByUsername(String username) {
        Query query=new Query(Criteria.where("username").is(username));
        mongoTemplate.remove(query,UserPartner.class,Consts.MONGODB_USER_COLLECTIN_NAME);
    }

}
