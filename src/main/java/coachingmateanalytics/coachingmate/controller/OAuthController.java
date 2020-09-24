package coachingmateanalytics.coachingmate.controller;

import coachingmateanalytics.coachingmate.dao.UserDao;
import coachingmateanalytics.coachingmate.entity.RequestToken;
import coachingmateanalytics.coachingmate.entity.UserPartner;
import coachingmateanalytics.coachingmate.service.OAuthService;
import coachingmateanalytics.coachingmate.utils.Consts;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * This Controller exercises an implementation of the core oauth process.
 *
 *
 */
@Controller
@RequestMapping("/auth")
public class OAuthController {
    private static final Logger logger = LoggerFactory.getLogger(OAuthController.class);

    @Autowired
    OAuthService oauthService;

    @Autowired
    UserDao userDao;

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
    @RequestMapping("/requestToken/{username}")
    public ResponseEntity<String> oauthRequestToken(@PathVariable String username) {
        RequestToken reqToken = oauthService.getRequestToken(requestTokenUrl, username);
        String result = "";
        if (reqToken != null) {
            result  = oauthService.getOAuthConfirmURL(reqToken.getToken());
            logger.info("the redirect url : " + result);
        }
        return ResponseEntity.ok(result);
    }

    /**
     * The third oauth leg. Provide the oauth token and the verifier value from the first two steps
     * to generate a user access token associated with the user. From this point on that user is opted in
     * to your program.
     * @param oauthToken
     * @param oauthVerifierValue
     * @return
     */
    @RequestMapping("/accessToken")
    void oauthAccessToken(@RequestParam(value = "oauth_token") String oauthToken,
                            @RequestParam(value = "oauth_verifier") String oauthVerifierValue) {
        if (oauthVerifierValue != null && !oauthVerifierValue.isEmpty()) {
            String accessTokenUrl = oauthAccessTokenUrl + Consts.URL_DELIMTER + Consts.OAUTH_VERIFIER + Consts.VALUE_DELIMTER+ oauthVerifierValue;
            oauthService.generateAccessToken(accessTokenUrl, oauthToken);
        }
    }




    /**
     * A convenience endpoint to display user data if it exists, or generate it if it doesn't.
     * It just calls all of the above functionality.
     * This should not be part of your actual implementation!
     * @param username
     * @return
     */
    @RequestMapping(value = "/userAccessToken", method = RequestMethod.POST)
    public String generateToken(@RequestParam String username) {
        UserPartner userPartner = userDao.findUserByUsername(username);
        if (userPartner.getUserAccessToken() != null) {
            return userPartner.getUsername();
        } else {
            return "redirect:/auth/requestToken/" + username;
        }
    }

}