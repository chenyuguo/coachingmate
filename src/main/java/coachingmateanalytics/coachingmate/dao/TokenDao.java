package coachingmateanalytics.coachingmate.dao;

import coachingmateanalytics.coachingmate.entity.RequestToken;
import coachingmateanalytics.coachingmate.entity.UserPartner;
import coachingmateanalytics.coachingmate.utils.Consts;
import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @Date: 9/9/20 15:40
 * @Description:
 */
@Component
public class TokenDao {
    private static final Logger logger = LoggerFactory.getLogger(TokenDao.class);

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    @Autowired
    private MongoTemplate mongoTemplate;


    // It stores request token and secret in the requestToken Repo
    public RequestToken saveRequestToken(String userName, String token, String secret) {
        RequestToken reqToken = new RequestToken();
        reqToken.setToken(token);
        reqToken.setSecret(secret);
        reqToken.setUserName(userName);
        try {
            redisTemplate.opsForValue().set(token, JSON.toJSONString(reqToken));
            mongoTemplate.save(JSON.toJSONString(reqToken), Consts.MONGODB_TOKEN_COLLECTIN_NAME);
            return reqToken;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public RequestToken findByToken(String reqToken){
        String o = (String)redisTemplate.opsForValue().get(reqToken);
        return JSON.parseObject(o, RequestToken.class);
    }



    // It stores user access token with the user name.
    public void saveAccessToken(String token, String secret, Long userId, String userName) {
        UserPartner newUser = new UserPartner();
        newUser.setUserId(userId);
        newUser.setUserName(userName);
        newUser.setUserAccessToken(token);
        newUser.setUserAccessSecret(secret);
        redisTemplate.opsForHash().put("userPartners", userId, JSON.toJSONString(newUser));
    }

    public UserPartner findOne(long userId){
        String userPartners = (String) redisTemplate.opsForHash().get("userPartners", userId);
        return JSON.parseObject(userPartners, UserPartner.class);
    }

    // it returns all UserPartner
    public List<UserPartner> findAll(){
        Map<Object, Object> userPartners = redisTemplate.opsForHash().entries("userPartners");
        List<UserPartner> res = new LinkedList<>();
        Set<Map.Entry<Object, Object>> entries = userPartners.entrySet();
        for (Map.Entry<Object, Object> entry : entries) {
            String value = (String) entry.getValue();
            res.add(JSON.parseObject(value, UserPartner.class));
        }
        return res;
    }
}


