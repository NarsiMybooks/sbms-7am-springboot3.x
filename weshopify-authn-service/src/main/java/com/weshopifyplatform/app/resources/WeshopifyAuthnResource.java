package com.weshopifyplatform.app.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.weshopifyplatform.app.beans.AccessTokenReq;
import com.weshopifyplatform.app.beans.AccessTokenResp;
import com.weshopifyplatform.app.services.WeshopifyAuthnService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api")
@Slf4j
public class WeshopifyAuthnResource {
	
	@Autowired
	private WeshopifyAuthnService weshopifyAuthnService;

	@PostMapping(value = "/token")
	public ResponseEntity<AccessTokenResp> authenticate(@RequestBody AccessTokenReq tokenReq){
		log.info("WeshopifyAuthnResource--->Token Req Is:\t"+tokenReq.toString());
		return ResponseEntity.ok(weshopifyAuthnService.authnWithIdp(tokenReq.getUsername(), tokenReq.getPassword()));
	}
}
