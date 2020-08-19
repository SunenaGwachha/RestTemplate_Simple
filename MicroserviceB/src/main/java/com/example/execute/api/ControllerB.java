package com.example.execute.api;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;



@RestController
public class ControllerB {
	private String baseUrl = "http://localhost:1081/getA/";

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/getB")
   public String getnameB(){
        return "This is from microserviceB";
    }
    
    @GetMapping("/callA")
    public ResponseEntity<String> getFromA(){
    	
    	return restTemplate.getForEntity(baseUrl, String.class);
    }
    	//OR
    	
 //   @GetMapping("/callA")
 //   public String getFromA(){ 	
    	
//    	HttpHeaders headers = new HttpHeaders();
//        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
//        HttpEntity <String> entity = new HttpEntity<String>(headers);
//         return  restTemplate.exchange(baseUrl, HttpMethod.GET, entity, String.class).getBody();      		 
//        		 
//      }   
         
         
     
}
