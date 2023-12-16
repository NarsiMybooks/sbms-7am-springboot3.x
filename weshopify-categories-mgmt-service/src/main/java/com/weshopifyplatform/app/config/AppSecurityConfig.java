package com.weshopifyplatform.app.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import lombok.extern.slf4j.Slf4j;

@Configuration
@Slf4j
public class AppSecurityConfig {

	@Autowired
	private IdpAuthzService authnService;
	
	@Autowired
	private AuthenticationEntryPoint entryPoint;
	
	@Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests((authz) -> {
			try {
				authz
				        .anyRequest().authenticated()
				        .and().csrf().disable().anonymous().disable()
				        .addFilterBefore(new TokenValidationFilter(authnService,entryPoint), 
				        		BasicAuthenticationFilter.class);
				        //.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
				        //.and().httpBasic();
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
        		
        return http.build();
    }
	
	@Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
		log.info("ingnoring the security");
		return (web) -> web.ignoring().requestMatchers("/swagger-ui.html","/swagger-ui/**","/v3/api-docs/**");
    }
}