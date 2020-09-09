package coachingmateanalytics.coachingmate.controller;

import coachingmateanalytics.coachingmate.dao.TokenDao;
import coachingmateanalytics.coachingmate.entity.RequestToken;
import coachingmateanalytics.coachingmate.entity.UserPartner;
import coachingmateanalytics.coachingmate.service.OAuthService;
import coachingmateanalytics.coachingmate.utils.Consts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * This Controller exercises an implementation of the core oauth process.
 *
 *
 */
@Controller
@RequestMapping("/auth")
public class OAuthController
{
    @Autowired
    OAuthService oauthService;

    @Autowired
    TokenDao tokenDao;

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

    /**
     * The third oauth leg. Provide the oauth token and the verifier value from the first two steps
     * to generate a user access token associated with the user. From this point on that user is opted in
     * to your program.
     * @param oauthToken
     * @param oauthVerifierValue
     * @param model
     * @return
     */
    @RequestMapping("/accessToken")
    String oauthAccessToken(@RequestParam(value = "oauth_token") String oauthToken,
                            @RequestParam(value = "oauth_verifier") String oauthVerifierValue, Model model) {
        if (oauthVerifierValue != null && !oauthVerifierValue.isEmpty()) {
            String accessTokenUrl = oauthAccessTokenUrl + Consts.URL_DELIMTER + Consts.OAUTH_VERIFIER + Consts.VALUE_DELIMTER+ oauthVerifierValue;
            oauthService.generateAccessToken(accessTokenUrl, oauthToken);
        }
        model.addAttribute("userPartners", tokenDao.findAll());
        return "redirect:/auth/userPartner";
    }

    /**
     * A convenience page to display a list of all current users opted in to this program.
     * This should not be part of your actual implementation!
     * @param model
     * @return
     */
    @RequestMapping(value = "/userPartner", method = RequestMethod.GET)
    public String usersList(Model model)
    {
        model.addAttribute("userPartners", tokenDao.findAll());
        return "userPartner";
    }


    /**
     * A convenience endpoint to display user data if it exists, or generate it if it doesn't.
     * It just calls all of the above functionality.
     * This should not be part of your actual implementation!
     * @param userId
     * @param userName
     * @param model
     * @return
     */
    @RequestMapping(value = "/userAccessToken", method = RequestMethod.POST)
    public String generateToken(@RequestParam Long userId, @RequestParam String userName, Model model) {
        UserPartner userPartner = tokenDao.findOne(userId);
        if (userPartner != null) {
            String str = Consts.ERROR_MSG + userId;
            model.addAttribute("errorMessage", str);
            return usersList(model);
        } else {
            return "redirect:/auth/requestToken/" + userId + "/" + userName;
        }
    }


}