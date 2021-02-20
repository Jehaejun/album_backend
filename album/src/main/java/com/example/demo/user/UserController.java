package com.example.demo.user;

import java.net.URI;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class UserController {
	
	@Autowired
	private UserRepository userRepository;

	@PostMapping(value="/api/user/join")
	@Transactional
	public void join(@RequestBody User user) {
		userRepository.save(user);
	}
	
	// 로그아웃
	@PostMapping(value="/api/user/2")
	public void logOut(HttpServletRequest request) {
		String API_SERVER_HOST  = "https://kapi.kakao.com";
		String SEARCH_PLACE_KEYWORD_PATH = "/v1/user/unlink";
		
		RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        
		String acToken = request.getHeader("access_token");
		headers.add("Authorization", "Bearer " + acToken);
		
		URI url = URI.create(API_SERVER_HOST+SEARCH_PLACE_KEYWORD_PATH);
        RequestEntity<String> rq = new RequestEntity<>(headers, HttpMethod.POST, url);
        ResponseEntity<String> re = restTemplate.exchange(rq, String.class);
        
        System.out.println(re.toString());
	}
	
	// 사용자 정보 가져오기
	@PostMapping(value="/api/user/3")
	public String getUserData(HttpServletRequest request) {
		String API_SERVER_HOST  = "https://kapi.kakao.com";
		String SEARCH_PLACE_KEYWORD_PATH = "/v2/user/me";
		
		RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        
		String acToken = request.getHeader("access_token");
		headers.add("Authorization", "Bearer " + acToken);
		
		URI url = URI.create(API_SERVER_HOST+SEARCH_PLACE_KEYWORD_PATH);
        RequestEntity<String> rq = new RequestEntity<>(headers, HttpMethod.POST, url);
        ResponseEntity<String> re = restTemplate.exchange(rq, String.class);
        
        //restTemplate.getForEntity(rq, Map.class);
        
        System.out.println(re.getBody());
        return re.getBody();
	}
	
	// 토근 재발급
	@PostMapping(value="/api/user/4")
	public String getAcToken(HttpServletRequest request) {
		System.out.println("~~~~~");
		String API_SERVER_HOST  = "https://kauth.kakao.com";
		String SEARCH_PLACE_KEYWORD_PATH = "/oauth/token";
		
		RestTemplate restTemplate = new RestTemplate();
        
        MultiValueMap<String, String> parameters = new LinkedMultiValueMap<>();
        String rfToken = request.getHeader("refresh_token");
        
        System.out.println(rfToken);
        
        parameters.add("grant_type", "refresh_token");
        parameters.add("client_id", "client_id");
        parameters.add("refresh_token", rfToken);
        parameters.add("client_secret", "client_secret");
        
		
		URI url = URI.create(API_SERVER_HOST+SEARCH_PLACE_KEYWORD_PATH);
   //     RequestEntity<String> rq = new RequestEntity<>(parameters, HttpMethod.POST, url);
     //   ResponseEntity<String> re = restTemplate.exchange(rq, String.class);
        
        String responseData =  restTemplate.postForObject(API_SERVER_HOST+SEARCH_PLACE_KEYWORD_PATH, parameters, String.class);
        //(API_SERVER_HOST+SEARCH_PLACE_KEYWORD_PATH, String.class, parameters);
        //restTemplate.getForEntity(rq, Map.class);
        
        //System.out.println(test);
        return responseData;
	}
}


