package coachingmateanalytics.coachingmate.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

@RestController
public class GarminPushController {
    private static final Logger logger = LoggerFactory.getLogger(GarminPushController.class);

    
    //configure this url to enpoint configuration, and the garmin endpoint will transfer the data to this server
    @PostMapping("/push")
    public Response acceptPushedFile(HttpServletRequest request){
        logger.info("start push data");
        MultipartHttpServletRequest params=((MultipartHttpServletRequest) request);
        List<MultipartFile> files = ((MultipartHttpServletRequest) request)
                .getFiles("file");
        String name=params.getParameter("name");
        System.out.println("name:"+name);
        String id=params.getParameter("id");
        System.out.println("id:"+id);
        MultipartFile file = null;
        BufferedOutputStream stream = null;
        for (int i = 0; i < files.size(); ++i) {
            file = files.get(i);
            if (!file.isEmpty()) {
                try {
                    byte[] bytes = file.getBytes();
                    stream = new BufferedOutputStream(new FileOutputStream(
                            new File(file.getOriginalFilename())));
                    stream.write(bytes);
                    stream.close();
                } catch (Exception e) {
                    stream = null;
                    return Response.status(Response.Status.SERVICE_UNAVAILABLE) .header("Retry-After", "120")
                    .entity("Failed to process. Reason : " ) .type(MediaType.TEXT_PLAIN).build();
                }
            } else {
                return Response.status(Response.Status.SERVICE_UNAVAILABLE) .header("Retry-After", "120")
                    .entity("Failed to process. Reason : ") .type(MediaType.TEXT_PLAIN).build();
            }
        }
        return Response.status(Response.Status.ACCEPTED) .header("Location", "url")
                    .entity("Processed successfully.") .type(MediaType.TEXT_PLAIN).build();
//        try {
//            // The first part will be the original upload in bytes
//            BodyPartEntity dataPart = (BodyPartEntity) multiPart.getBodyParts().get(0).getEntity();
//
//            // The second part will be the uploadMetaData as Content-Type: application/json
//            String metadataStr = multiPart.getBodyParts().get(1).getEntityAs(String.class);
//            logger.info(metadataStr);
//            ObjectMapper mapper = new ObjectMapper();
//
//            // Parse the json string into a map for further use
//            Map<String, String> map = mapper.readValue(metadataStr, new TypeReference<Map<String, String>>() { });
//            // TODO do something with the meta data
//            logger.info(String.valueOf(map));
            // Convert the data part to byte array
//            byte[] data = IOUtils.toByteArray(dataPart.getInputStream());
//            String dataHead = new String(data, 0, 64, StandardCharsets.UTF_8);
//            String fileType;
//            if (dataHead.contains("Training")) {
//                fileType = "TCX";
//            } else if (dataHead.contains("gpx")) {
//                fileType = "GPX";
//            } else {
//                fileType = "FIT"; }
//            // TODO do something with the file
//            logger.info("accept Pushed File");
//            return Response.status(Response.Status.ACCEPTED) .header("Location", "url")
//                    .entity("Processed successfully.") .type(MediaType.TEXT_PLAIN).build();
//        } catch (Exception e) {
//            String errorMessage = e.getMessage();
//            logger.error(errorMessage, e);
//            return Response.status(Response.Status.SERVICE_UNAVAILABLE) .header("Retry-After", "120")
//                    .entity("Failed to process. Reason : " + errorMessage) .type(MediaType.TEXT_PLAIN).build();
//        }
    }

}
