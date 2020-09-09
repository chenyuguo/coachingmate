package coachingmateanalytics.coachingmate.dao;

import coachingmateanalytics.coachingmate.service.OauthConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class TokenProviderDao
{
private static final Logger logger = LoggerFactory.getLogger(TokenProviderDao.class);

    
    @Autowired
    OauthConfiguration oauthConfig;
    
    
    public String generateRequestTokenSecret(String url)
    {
        String tokenAndSecret = null;
        try{
            RestTemplate restTemplate = oauthConfig.getRestTemplate();
            ResponseEntity<String> result =  restTemplate.exchange(url, HttpMethod.GET,oauthConfig.httpEntity(), String.class);
            tokenAndSecret = result.getBody().toString();
          logger.info("value in tokenSecret is: {}",tokenAndSecret);
        }
        catch(Exception e){
            logger.error(e.getMessage());
        }
        
        return tokenAndSecret;
        
    }
    

}
