package coachingmateanalytics.coachingmate.controller;

import coachingmateanalytics.coachingmate.service.ActivityService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class GarminPushController {
    private static final Logger logger = LoggerFactory.getLogger(GarminPushController.class);
    public static final String STORE_PATH= "/Users/moushuai/intellijSpace/coachingmate/public/garmin_raw/";

    @Autowired
    ActivityService activityService;

    //configure this url to enpoint configuration, and the garmin endpoint will transfer the data to this server
    @PostMapping("/push")
    @ApiOperation(value = "push data url", notes = "configure this url to enpoint configuration, " +
            "and the garmin endpoint will transfer the data to this server")
    public ResponseEntity<String> acceptPushedFile1(@ApiParam(type = "MultipartFile") MultipartFile file, @ApiParam(type = "String") String uploadMetaData) {
        logger.debug("start push data");
        logger.info("uploadMetaData :" + uploadMetaData);
        HttpHeaders httpHeaders = new HttpHeaders();
        try {
//            byte[] data = IOUtils.toByteArray(file.getInputStream());
//            String dataHead = new String(data, 0, 47182);
//            logger.info("Activity Data" + dataHead);
//            Activity activity = new Activity("3f3d5af3-7847-413e-a9fe-47aeffb6de44", uploadMetaData);
//            activityService.saveActivity(activity);

        } catch (Exception e) {
            httpHeaders.set("Retry-After", "120");
            return ResponseEntity.status(503).headers(httpHeaders).body("Failed to process. Reason : " + e.getMessage());
        }
        httpHeaders.set("Location", "public/garmin_raw");
        return ResponseEntity.accepted().headers(httpHeaders).body("Accept the pushed file");

    }
}
