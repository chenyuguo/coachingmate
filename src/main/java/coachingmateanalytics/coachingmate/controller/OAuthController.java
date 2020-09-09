package coachingmateanalytics.coachingmate.controller;

import coachingmateanalytics.coachingmate.entity.RequestToken;
import coachingmateanalytics.coachingmate.service.OAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * This Controller exercises an implementation of the core oauth process.
 * 
 *
 */
@Controller
@EnableAutoConfiguration
@Component
@RequestMapping("/auth")
public class OAuthController
{
    @Autowired
    OAuthService oauthService;

    //The Garmin Connect API Oauth request_token URL
    @Value("${requestToken.url}")
    private String requestTokenUrl;

    //The Garmin Connect API Oauth access_token URL
    @Value("${oauthAccessToken.url}")
    private String oauthAccessTokenUrl;
    
    /**
     * Initiate the request_token portion of the 3-legged oauth process, then redirect to the oauthConfirm step
     * so the user can enter their Garmin Connect username and password.
     * @return A ResponseEntity sending the user to the manual oauthConfirm page.
     */
    @RequestMapping("/requestToken/{userId}/{userName}")
    public ResponseEntity<Object> oauthRequestToken(@PathVariable long userId, @PathVariable String userName) {
        ResponseEntity<Object> responseObject = null;
        RequestToken reqToken = oauthService.getRequestToken(requestTokenUrl,userId,userName);

        if (reqToken != null)
        { 
            responseObject = oauthService.oauthConfirm(reqToken.getToken());
        }
        return responseObject;
    }
}