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
        mongoTemplate.save(JSON.toJSONString(user), Consts.MONGODB_USER_COLLECTIN_NAME);
    }

    /***
     * @Description  use to test the garmin connect push feature
     * @Date 23:24 22/9/20
     * @param activity
     * @return {@link }
     */
    public void saveActivityFile(String activity){
        mongoTemplate.save(activity, Consts.MONGODB_ACTIVITY_COLLECTIN_NAME);
    }

    public UserPartner findUserByUserName(String userName) {
        Query query=new Query(Criteria.where("userName").is(userName));
        UserPartner user =  mongoTemplate.findOne(query , UserPartner.class);
        return user;
    }

    public int updateUser(UserPartner user) {
        Query query=new Query(Criteria.where("id").is(user.getUserId()));
        Update update= new Update().set("userName", user.getUsername()).set("passWord", user.getPassword());
        //更新查询返回结果集的第一条
        UpdateResult result =mongoTemplate.updateFirst(query,update,UserPartner.class);
        //更新查询返回结果集的所有
        // mongoTemplate.updateMulti(query,update,UserEntity.class);
        if(result!=null)
            return (int) result.getMatchedCount();
        else
            return 0;
    }

    @Override
    public void deleteUserById(Long id) {
        Query query=new Query(Criteria.where("id").is(id));
        mongoTemplate.remove(query,UserPartner.class);
    }

}
