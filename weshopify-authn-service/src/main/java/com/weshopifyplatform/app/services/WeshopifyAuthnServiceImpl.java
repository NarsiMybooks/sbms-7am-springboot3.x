package com.weshopifyplatform.app.services;

import java.math.BigInteger;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.weshopifyplatform.app.beans.AccessTokenReq;
import com.weshopifyplatform.app.beans.AccessTokenResp;
import com.weshopifyplatform.app.utils.AppConstants;

@Service
public class WeshopifyAuthnServiceImpl implements WeshopifyAuthnService {

	@Autowired
	private Environment env;
	
	@Autowired
	private RestTemplate rt;
	
	@Autowired
	private RedisTemplate<String, String> redisTemplate;
	
	@Override
	public AccessTokenResp authnWithIdp(String username, String password) {
		AccessTokenReq tokenReq = new AccessTokenReq();
		tokenReq.setUsername(username);
		tokenReq.setPassword(password);
		tokenReq.setClient_id(env.getProperty(AppConstants.CLIENT_ID));
		tokenReq.setClient_secret(env.getProperty(AppConstants.CLIENT_SCRETE));
		tokenReq.setGrant_type(env.getProperty(AppConstants.GRANT_TYPE));
		tokenReq.setAudience(env.getProperty(AppConstants.AUDIANCE));
		tokenReq.setScope(env.getProperty(AppConstants.SCOPES));
		
		String accessTokenUri = env.getProperty(AppConstants.ACCESS_TOKEN_URI);
		
		MultiValueMap<String, String> headerMap = new LinkedMultiValueMap();
		headerMap.add("Content-Type", "application/json");
		HttpEntity headerEntity = new HttpEntity(tokenReq,headerMap);
		
		ResponseEntity<AccessTokenResp> resp =  rt.exchange(accessTokenUri, HttpMethod.POST,headerEntity,AccessTokenResp.class);
		if(resp.getStatusCode()==HttpStatus.OK) {
			AccessTokenResp tokenResp = resp.getBody();
			storeTokenInCache(tokenResp);
			return tokenResp;
		}
		return null;
	}

	
	public String getUserProfile(String accessToken) {
		MultiValueMap<String, String> headerMap = new LinkedMultiValueMap();
		headerMap.add("Authorization", "Bearer "+accessToken);
		HttpEntity headerEntity = new HttpEntity(headerMap);
		
		ResponseEntity<String> resp = rt.exchange("https://dev-c7g863j05da8mwwv.us.auth0.com/userinfo",
				HttpMethod.GET, headerEntity, String.class);
		if(resp.getStatusCode()==HttpStatus.OK) {
			return resp.getBody();
		}
		return null;
	}
	
	private void storeTokenInCache(AccessTokenResp tokenResp) {
		String userProfile = getUserProfile(tokenResp.getAccess_token());
		SetOperations<String, String> redisSet = redisTemplate.opsForSet();
		
		redisSet.getOperations().opsForValue().set(tokenResp.getAccess_token(), userProfile);
		//redisSet.add(tokenResp.getAccess_token(), userProfile);	
		JSONObject jsonObject = new JSONObject(userProfile);
		if(jsonObject.has("email")) {
			String email = jsonObject.getString("email");
			storeTokenDetailsInCache(email, tokenResp.getExpires_in());
		}
		
	}
	
	private void storeTokenDetailsInCache(String email,long token_expiry) {
		SetOperations<String, String> redisSet = redisTemplate.opsForSet();
		redisSet.getOperations().opsForValue().set(email, String.valueOf(token_expiry));
			
	}
}
