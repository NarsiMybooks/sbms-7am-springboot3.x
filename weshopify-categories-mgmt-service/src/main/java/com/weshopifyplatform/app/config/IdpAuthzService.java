package com.weshopifyplatform.app.config;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.json.JSONObject;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

@Component
public class IdpAuthzService {

	ValueOperations<String, String> valueOps = null;

	IdpAuthzService(RedisTemplate<String, String> redisTemplate) {
		SetOperations<String, String> redisSet = redisTemplate.opsForSet();
		valueOps = redisSet.getOperations().opsForValue();
	}

	public Authentication authzUser(String accessToken) {
		Authentication authn = null;
		String userProfile = getUserProfile(accessToken);
		if(userProfile != null) {
			String expiry = getExpiryOfToken(userProfile);
			boolean isTokenValid = isTokenValid(expiry);
			if(isTokenValid) {
				JSONObject jsonObject = new JSONObject(userProfile);
				String email = jsonObject.getString("email");
				List<GrantedAuthority> rolesList = new ArrayList<>();
				rolesList.add(new SimpleGrantedAuthority("weshopify_user"));
				authn = new UsernamePasswordAuthenticationToken(email, null, rolesList);
			}
		}
		
		return authn;
	}

	private String getUserProfile(String accessToken) {
		String userProfile = valueOps.get(accessToken);
		return userProfile;
	}

	private String getExpiryOfToken(String userProfile) {
		JSONObject jsonObject = new JSONObject(userProfile);
		String email = jsonObject.getString("email");
		String expiryInSeconds = valueOps.get(email);
		return expiryInSeconds;
	}

	private boolean isTokenValid(String expiryInSeconds) {
		boolean isTokenValid = false;
		long tokenExpiryInSeconds = Long.valueOf(expiryInSeconds);
		Date tokenDate = expiryDate(tokenExpiryInSeconds);
		Date today = new Date();
		if (!tokenDate.before(today)) {
			isTokenValid = true;
		}
		return isTokenValid;
	}

	private Date expiryDate(long tokenExpiry) {
		Date date = new Date();
		System.out.println(date);
		long time = date.getTime() + tokenExpiry * 1000;
		Date updatedDate = new Date(time);
		return updatedDate;
	}
}
