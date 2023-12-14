package com.weshopifyplatform.app.services;

import com.weshopifyplatform.app.beans.AccessTokenResp;

public interface WeshopifyAuthnService {

	public AccessTokenResp authnWithIdp(String username, String password);
}
