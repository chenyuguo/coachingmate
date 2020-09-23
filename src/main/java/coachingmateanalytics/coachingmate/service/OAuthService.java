package coachingmateanalytics.coachingmate.service;

import coachingmateanalytics.coachingmate.dao.TokenDao;
import coachingmateanalytics.coachingmate.entity.RequestToken;
import coachingmateanalytics.coachingmate.dao.TokenProviderDao;
import coachingmateanalytics.coachingmate.utils.Consts;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.net.URI;
import java.net.URISyntaxException;

@Service
public class OAuthService {
    private static final Logger logger = LoggerFactory.getLogger(OAuthService.class);

    @Autowired
    TokenProviderDao tokenProvider;

    @Autowired
    TokenDao tokenDao;

    @Value("${oauthConfirm.url}")
    private String oauthConfirmUrl;

    @Value("${callBackURL.url}")
    private String callBackUrlValue;

    // It generates requesttoken and secret and save in the redis repository
    public RequestToken getRequestToken(String url, String username) {
        String reqTokenSecret = tokenProvider.generateRequestTokenSecret(url);
        if (reqTokenSecret.contains(Consts.OAUTH_TOKEN)) {
            String[] tokenAndSecret = reqTokenSecret.split(Consts.VARIABLE_DELIMTER);
            String[] tokenValue = tokenAndSecret[0].split(Consts.VALUE_DELIMTER);
            String[] secretValue = tokenAndSecret[1].split(Consts.VALUE_DELIMTER);
            RequestToken reqToken = tokenDao.saveRequestToken(username, tokenValue[1],secretValue[1]);
            return reqToken;
        } else {
            return null;
        }
    }

    public String getOAuthConfirmURL(String accessToken) {
        return oauthConfirmUrl + Consts.URL_DELIMTER + Consts.OAUTH_TOKEN + Consts.VALUE_DELIMTER + accessToken + Consts.VARIABLE_DELIMTER + Consts.CALLBACK_URL + '=' + callBackUrlValue;
    }

    public void generateAccessToken(String url,String oauthTokenValue) {
        String accessTokenSecret = tokenProvider.generateAccessTokenSecret(url, oauthTokenValue);
        if (accessTokenSecret.contains(Consts.OAUTH_TOKEN)) {
            String[] tokenAndSecret = accessTokenSecret.split(Consts.VARIABLE_DELIMTER);
            String[] tokenValue = tokenAndSecret[0].split(Consts.VALUE_DELIMTER);
            String[] secretValue = tokenAndSecret[1].split(Consts.VALUE_DELIMTER);
            Long id = Long.parseLong(tokenAndSecret[2]);
            String name = tokenAndSecret[3];
            tokenDao.saveAccessToken(tokenValue[1], secretValue[1], id, name);
        }

    }

}