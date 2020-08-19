package com.example.execute.api;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;



@RestController
public class ControllerA {
	private String baseUrl = "http://localhost:1082/getB/";

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/getA")
   public String getnameB(){
        return "This is from microserviceA";
    }
    
    @GetMapping("/callB")
    public String getFromB(){
    	
    	HttpHeaders headers = new HttpHeaders();
    	
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity <String> entity = new HttpEntity<String>(headers);
        return  restTemplate.exchange(baseUrl, HttpMethod.GET, entity, String.class).getBody();    
        
        		 
     }
}
