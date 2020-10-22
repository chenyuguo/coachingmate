package coachingmateanalytics.coachingmate.controller;

import coachingmateanalytics.coachingmate.entity.Statistic;
import coachingmateanalytics.coachingmate.service.ActivityService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import hirondelle.date4j.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import net.studioblueplanet.fitreader.*;

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
            JSONObject jsonObject = JSON.parseObject(uploadMetaData);
            String access_token = jsonObject.getString("oauthToken");
            FitRecordRepository repository;
            FitReader           reader;
            FitRecord           record;
            reader = FitReader.getInstance();
            repository = reader.readInputStream(file.getInputStream());
            record = repository.getFitRecord("session");
            String statistic_id = record.getStringValue(0, "statistic_id");
            String user_id = record.getStringValue(0, "user_id");
            String sport = record.getStringValue(0, "sport");
            DateTime start_time = record.getTimeValue(0, "start_time");
            double start_position_lat = record.getLatLonValue(0, "start_position_lat");
            double start_position_long = record.getLatLonValue(0, "start_position_long");
            double total_elapsed_time = record.getElapsedTimeValue(0, "total_elapsed_time");
            double total_distance = record.getDistanceValue(0, "total_distance");
            double total_cycles = record.getDistanceValue(0, "total_cycles");
            double avg_stroke_count = record.getIntValue(0, "avg_stroke_count");
            double avg_stroke_distance = record.getDistanceValue(0, "avg_stroke_distance");
            double total_calories = record.getDistanceValue(0, "total_calories");
            double avg_speed = record.getSpeedValue(0, "avg_speed");
            double max_speed = record.getSpeedValue(0, "max_speed");
            double avg_power = record.getDistanceValue(0, "avg_power");
            double max_power = record.getDistanceValue(0, "max_power");
            double total_ascent = record.getDistanceValue(0, "total_ascent");
            double total_descent = record.getDistanceValue(0, "total_descent");
            int num_laps = record.getIntValue(0, "num_laps");
            double training_stress_score = record.getDistanceValue(0, "training_stress_score");
            double intensity_factor = record.getDistanceValue(0, "intensity_factor");
            double pool_length = record.getDistanceValue(0, "pool_length");
            int threshold_power = record.getIntValue(0, "threshold_power");
            int avg_cadence = record.getIntValue(0, "avg_cadence");
            int max_cadence = record.getIntValue(0, "max_cadence");
            double total_fat_calories = record.getDistanceValue(0, "total_fat_calories");
            int normalized_power = record.getIntValue(0, "normalized_power");
            int num_active_lengths = record.getIntValue(0, "num_active_lengths");
            int sub_sport = record.getIntValue(0, "sub_sport");


            Statistic statistic = new Statistic(access_token,statistic_id, user_id, sport, start_time.toString(),
                                                start_position_lat, start_position_long, total_elapsed_time, total_distance, total_cycles,
                                                avg_stroke_count , avg_stroke_distance, total_calories, avg_speed, max_speed,
                                                avg_power, max_power, total_ascent, total_descent, num_laps,
                                                training_stress_score, intensity_factor, pool_length, threshold_power, avg_cadence,
                                                max_cadence, total_fat_calories, normalized_power, num_active_lengths, sub_sport);
            logger.info("finishing parse data ");
            activityService.saveActivity(statistic);
        } catch (Exception e) {
            httpHeaders.set("Retry-After", "120");
            return ResponseEntity.status(503).headers(httpHeaders).body("Failed to process. Reason : " + e.getMessage());
        }
        httpHeaders.set("Location", "public/garmin_raw");
        return ResponseEntity.accepted().headers(httpHeaders).body("Accept the pushed file");

    }
}
