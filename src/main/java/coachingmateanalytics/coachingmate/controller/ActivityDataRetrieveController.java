package coachingmateanalytics.coachingmate.controller;

import coachingmateanalytics.coachingmate.entity.Statistic;
import coachingmateanalytics.coachingmate.service.ActivityService;
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
    public ResponseEntity<List<Statistic>> retrieveDataByUser(@RequestParam("username") String username){
        List<Statistic> allByUsername = activityService.findAllByUsername(username);
        return ResponseEntity.ok(allByUsername);
    }
}
