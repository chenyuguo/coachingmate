package coachingmateanalytics.coachingmate.controller;


import coachingmateanalytics.coachingmate.dao.UserDao;
import coachingmateanalytics.coachingmate.entity.Activity;
import coachingmateanalytics.coachingmate.service.ActivityService;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.jersey.multipart.BodyPartEntity;
import com.sun.jersey.multipart.MultiPart;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;

import javax.ws.rs.core.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import javax.ws.rs.Consumes;
import javax.ws.rs.core.Response;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Map;
@RestController
public class GarminPushController {
    private static final Logger logger = LoggerFactory.getLogger(GarminPushController.class);
    public static final String STORE_PATH= "/Users/moushuai/intellijSpace/coachingmate/public/garmin_raw/";

    @Autowired
    ActivityService activityService;

    
    //configure this url to enpoint configuration, and the garmin endpoint will transfer the data to this server
    @PostMapping("/push")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Response acceptPushedFile(@RequestParam("file") MultiPart multiPart){
        logger.info("start push data");
        // The first part will be the original upload in bytes
        BodyPartEntity dataPart = (BodyPartEntity) multiPart.getBodyParts().get(0).getEntity();

        // The second part will be the uploadMetaData as Content-Type: application/json
        String metadataStr = multiPart.getBodyParts().get(1).getEntityAs(String.class);
        ObjectMapper mapper = new ObjectMapper();
        // Parse the json string into a map for further use Map<String, String> map =
        try {
            Map<String, String> map = mapper.readValue(metadataStr, new TypeReference<Map<String, String>>() {
            });

            // TODO do something with the meta data
            logger.info("metadataStr :  " + metadataStr);

            // Convert the data part to byte array
            byte[] data = IOUtils.toByteArray(dataPart.getInputStream());
            String dataHead = new String(data, 0, 64, StandardCharsets.UTF_8);

            logger.info("dataHead : " + dataHead);

        } catch (IOException e) {
            String errorMessage = e.getMessage();
            logger.error(errorMessage, e);
            return Response.status(Response.Status.SERVICE_UNAVAILABLE) .header("Retry-After", "120")
                    .entity("Failed to process. Reason : " + errorMessage) .type(MediaType.TEXT_PLAIN).build();

        }

        return Response.status(Response.Status.ACCEPTED) .header("Location", "public/garmin_raw")
                .entity("Processed successfully.") .type(MediaType.TEXT_PLAIN).build();
    }


    //configure this url to enpoint configuration, and the garmin endpoint will transfer the data to this server
    @PostMapping("/push1")
    public ResponseEntity<String> acceptPushedFile1(MultipartFile file) {
        logger.info("start push data");
        HttpHeaders httpHeaders = new HttpHeaders();
        try {
            storeFileinpath(file);
            byte[] bytes = file.getBytes();
            String s = new String(bytes);
            Activity activity = new Activity("3f3d5af3-7847-413e-a9fe-47aeffb6de44", s);
            activityService.saveActivity(activity);
        } catch (Exception e) {
            httpHeaders.set("Retry-After", "120");
            return ResponseEntity.status(503).headers(httpHeaders).body("Failed to process. Reason : " + e.getMessage());
        }
        httpHeaders.set("Location", "public/garmin_raw");
        return ResponseEntity.accepted().headers(httpHeaders).body("Accept the pushed file");

    }


    public static void storeFileinpath(MultipartFile file ) throws IOException {
        File file1 = new File(STORE_PATH + file.getOriginalFilename());
        file1.createNewFile();
        file.transferTo(file1);

    }


}
