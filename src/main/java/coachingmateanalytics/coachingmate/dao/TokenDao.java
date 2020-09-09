package coachingmateanalytics.coachingmate.dao;

import coachingmateanalytics.coachingmate.entity.RequestToken;
import com.alibaba.fastjson.JSON;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @Auther: Saul
 * @Date: 9/9/20 15:40
 * @Description:
 */
@Component
public class TokenDao {
    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    // It stores request token and secret in the requestToken Repo
    public RequestToken saveRequestToken(long userId, String userName, String token, String secret) {
        RequestToken reqToken = new RequestToken();
        reqToken.setToken(token);
        reqToken.setSecret(secret);
        reqToken.setUserId(userId);
        reqToken.setUserName(userName);

        try {
            redisTemplate.opsForValue().set(String.valueOf(userId), JSON.toJSONString(reqToken));
            return reqToken;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
