package com.app.controller;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;


@RestController
public class RestTemplateClient {

	@GetMapping("/getdata")
	public String getData() {
		String s = new String();
		
		String plainCreds="root:root@123";
		byte[] plainCredsByte=plainCreds.getBytes();
		String encodeBase64String = Base64.encodeBase64String(plainCredsByte);
		
		HttpHeaders headers= new HttpHeaders();
		headers.add("Authorization", "Basic "+ encodeBase64String);		
		HttpEntity<String> requestWithHeaders=new HttpEntity<String>(headers);
		
		String apiUrl="http://localhost:8081/getdata";
		RestTemplate rt=new RestTemplate();
		ResponseEntity<String> responseEntity = rt.exchange(apiUrl, HttpMethod.GET,requestWithHeaders ,String.class);
		s = responseEntity.getBody();
		return s;
	}
}
