package coachingmateanalytics.coachingmate.controller;


import coachingmateanalytics.coachingmate.dao.UserDao;
import coachingmateanalytics.coachingmate.entity.Activity;
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

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.ws.rs.Consumes;
import javax.ws.rs.core.Response;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;

@RestController
public class GarminPushController {
    private static final Logger logger = LoggerFactory.getLogger(GarminPushController.class);
    public static final String storePath = "/Users/moushuai/intellijSpace/coachingmate/public/garmin_raw/";
    @Autowired
    UserDao userDao;

    
    //configure this url to enpoint configuration, and the garmin endpoint will transfer the data to this server
    @PostMapping("/push")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Response acceptPushedFile(@RequestParam("file") MultiPart multiPart){
        logger.info("start push data");
//        if (file.isEmpty()) return ResponseEntity.badRequest().body("the pushed file is empty");

        /**  输出文件到 public/garmin_raw/
        try {
            File file1 = new File("/Users/moushuai/intellijSpace/coachingmate/public/garmin_raw/" + file.getOriginalFilename());
            file1.createNewFile();
            file.transferTo(file1);
        } catch (IOException e) {
            e.printStackTrace();
        }
         */


        /*********************************************************

        StringBuffer sb = new StringBuffer();
        BufferedReader bufferedReader = null;
        String content = "";

        try {

            bufferedReader =  new BufferedReader(new InputStreamReader(file.getInputStream()))  ; //new BufferedReader(new InputStreamReader(inputStream));
            char[] charBuffer = new char[128];
            int bytesRead;
            while ( (bytesRead = bufferedReader.read(charBuffer)) != -1 ) {
                sb.append(charBuffer, 0, bytesRead);
            }

        } catch (IOException ex) {
           logger.error(String.valueOf(ex.getStackTrace()));
        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException ex) {
                    logger.error(String.valueOf(ex.getStackTrace()));
                }
            }
        }
        content = sb.toString();
        logger.info("content : " + content);

         ******************************************************************************/
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
            e.printStackTrace();
//            HttpHeaders httpHeaders = new HttpHeaders();
//            httpHeaders.set("Retry-After", "120");
//            return ResponseEntity.status(503).headers(httpHeaders).body("Failed to process. Reason : " + e.getMessage());
            String errorMessage = e.getMessage();
            logger.error(errorMessage, e);
            return Response.status(Response.Status.SERVICE_UNAVAILABLE) .header("Retry-After", "120")
                    .entity("Failed to process. Reason : " + errorMessage) .type(MediaType.TEXT_PLAIN).build();

        }


//        HttpHeaders httpHeaders = new HttpHeaders();
//        httpHeaders.set("Location", "public/garmin_raw");
//        return ResponseEntity.accepted().headers(httpHeaders).body("Accept the pushed file");
        return Response.status(Response.Status.ACCEPTED) .header("Location", "public/garmin_raw")
                .entity("Processed successfully.") .type(MediaType.TEXT_PLAIN).build();
    }


    //configure this url to enpoint configuration, and the garmin endpoint will transfer the data to this server
    @PostMapping("/push1")
    public ResponseEntity acceptPushedFile1(HttpServletRequest request) {
        logger.info("start push data");

        MultipartHttpServletRequest params=(MultipartHttpServletRequest) request;
        List<MultipartFile> files = ((MultipartHttpServletRequest) request)
                .getFiles("file");
        String uploadId=params.getParameter("uploadId");
        logger.info("uploadId:"+uploadId);
        String oauthToken=params.getParameter("oauthToken");
        logger.info("oauthToken:"+oauthToken);
        logger.info("file_size:" + files.size());
        MultipartFile file = null;
        for (int i = 0; i < files.size(); ++i) {
            file = files.get(i);
            if (!file.isEmpty()) {
                try {
                    storeFileinpath(file);
                    byte[] bytes = file.getBytes();
                    String s = new String(bytes);
                    String activity = JSONObject.toJSONString(new Activity("testtoken", s));
                    userDao.saveActivityFile(activity);
                } catch (Exception e) {
                    HttpHeaders httpHeaders = new HttpHeaders();
                    httpHeaders.set("Retry-After", "120");
                    return ResponseEntity.status(503).headers(httpHeaders).body("Failed to process. Reason : " + e.getMessage());
                }
            } else {
                HttpHeaders httpHeaders = new HttpHeaders();
                httpHeaders.set("Retry-After", "120");
                return ResponseEntity.status(503).headers(httpHeaders).body("Failed to process. Reason : " +"file is empty");
            }
        }
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("Location", "public/garmin_raw");
        return ResponseEntity.accepted().headers(httpHeaders).body("Accept the pushed file");

    }

    /**
     * 16进制直接转换成为字符串(无需Unicode解码)
     * @param hexStr
     * @return
     */
    public static String hexStr2Str(String hexStr) {
        String str = "0123456789abcdef";
        char[] hexs = hexStr.toCharArray();
        byte[] bytes = new byte[hexStr.length() / 2];
        int n;
        for (int i = 0; i < bytes.length; i++) {
            n = str.indexOf(hexs[2 * i]) * 16;
            n += str.indexOf(hexs[2 * i + 1]);
            bytes[i] = (byte) (n & 0xff);
        }
        return new String(bytes);
    }

    public static void storeFileinpath(MultipartFile file ) throws IOException {
        PrintStream printer = null;
        BufferedOutputStream stream = null;
        byte[] bytes = file.getBytes();
        stream = new BufferedOutputStream(new FileOutputStream(
                new File(storePath + file.getOriginalFilename() + ".json")));
        printer = new PrintStream(stream);
//                    stream.write(bytes);
        String s = new String(bytes);
        printer.println(s);
        stream.close();
        printer.close();

    }
}
