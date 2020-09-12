package coachingmateanalytics.coachingmate.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableAutoConfiguration
@Component
@RequestMapping("/push")
public class GarminPushController
{

    private static final Logger logger = LoggerFactory.getLogger(GarminPushController.class);
    
    //configure this url to enpoint configuration, and the garmin endpoint will transfer the data to this server
    @RequestMapping(value = "/pushactivity", method = RequestMethod.POST)
    public String acceptEventPushedActivities() {
        System.out.println("push success");
        return "push success";
    }

}
