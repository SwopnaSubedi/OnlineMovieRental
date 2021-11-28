package com.groupinsy.omr.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

@Controller
public class MovieController {

	
	@Value("${movielocation}")
	String movieLocation;
	
	
	@GetMapping("/video-play/{videofile}")
    public ResponseEntity<byte[]> streamFile(
    		@PathVariable("videofile") String videofile,
            HttpServletRequest request,
            HttpServletResponse response) {

        File file = new File(movieLocation+"/"+videofile+".mp4");
        if (!file.isFile()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);

        }

//        StreamingResponseBody stream = out -> {
//
//            try {
//                final InputStream inputStream = new FileInputStream(file);
//
//                byte[] bytes = new byte[1024];
//                int length;
//                while ((length = inputStream.read(bytes)) >= 0) {
//                    out.write(bytes, 0, length);
//                }
//                inputStream.close();
//                out.flush();
//
//            } catch (final Exception e) {
//                
//            }
//
//        };
//
        final HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "video/mp4");
        headers.add("Content-Length", Long.toString(file.length()));
        
        byte[] bytes;
		try {
			
			bytes = StreamUtils.copyToByteArray(new FileInputStream(file));
			
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}


//        return ResponseEntity.ok().headers(headers).body(stream);
        
        return ResponseEntity
                .ok()
                .headers(headers)
                .body(bytes);
    }
	
	
	@GetMapping(value="/thumbnail/{videofile}",produces = MediaType.IMAGE_JPEG_VALUE)
    public ResponseEntity<byte[]> getThumbnail(
    		@PathVariable("videofile") String videofile,
            HttpServletRequest request,
            HttpServletResponse response) throws IOException {

    	 File file = new File(movieLocation+"/thumbnails/"+videofile+".jpg");
        if (!file.isFile()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        byte[] bytes = StreamUtils.copyToByteArray(new FileInputStream(file));
        
        return ResponseEntity
                .ok()
                .contentType(MediaType.IMAGE_JPEG)
                .body(bytes);
    }
	
	
}
