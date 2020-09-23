package coachingmateanalytics.coachingmate.dao;

import coachingmateanalytics.coachingmate.entity.RequestToken;
import coachingmateanalytics.coachingmate.entity.UserPartner;
import coachingmateanalytics.coachingmate.utils.Consts;
import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
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

    @Autowired
    private MongoTemplate mongoTemplate;

    // It stores request token and secret in the requestToken Repo
    public RequestToken saveRequestToken(String username, String token, String secret) {
        RequestToken reqToken = new RequestToken(username,token,secret);
        try {
            mongoTemplate.save(JSON.toJSONString(reqToken), Consts.MONGODB_TOKEN_COLLECTIN_NAME);
            return reqToken;
        } catch (Exception e) {
            logger.error(e.getMessage());
            return null;
        }
    }

    public RequestToken findByToken(String reqToken){
        Query query = Query.query(Criteria.where("token").is(reqToken));
        RequestToken token = mongoTemplate.findOne(query, RequestToken.class, Consts.MONGODB_TOKEN_COLLECTIN_NAME);
        return token;
    }


    // It stores user access token with the user name.
    public void saveAccessToken(String token, String secret, Long userId, String username) {
        Query query = Query.query(Criteria.where("username").is(username));
        Update update = Update.update("userAccessToken", token).set("userAccessSecret", secret);
        mongoTemplate.updateFirst(query, update, UserPartner.class, Consts.MONGODB_USER_COLLECTIN_NAME);
    }

}


