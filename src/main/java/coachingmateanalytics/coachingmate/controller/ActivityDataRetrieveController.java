package coachingmateanalytics.coachingmate.controller;

import coachingmateanalytics.coachingmate.entity.Activity;
import coachingmateanalytics.coachingmate.service.ActivityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @Date: 24/9/20 15:51
 * @Description:
 */
@Controller
@RequestMapping("/activity")
public class ActivityDataRetrieveController {
    @Autowired
    ActivityService activityService;

    @GetMapping("/getall")
    public ResponseEntity<List<Activity>> retrieveDataByUser(@RequestParam("username") String username){
        List<Activity> allByUsername = activityService.findAllByUsername(username);
        return ResponseEntity.ok(allByUsername);
    }
}
