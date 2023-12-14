/*
 * package com.weshopifyplatform.app.config;
 * 
 * import java.util.Arrays; import java.util.List;
 * 
 * import org.springframework.context.annotation.Bean; import
 * org.springframework.context.annotation.Configuration; import
 * org.springframework.security.config.Customizer; import
 * org.springframework.security.config.annotation.web.builders.HttpSecurity;
 * import org.springframework.security.config.annotation.web.configuration.
 * EnableWebSecurity; import
 * org.springframework.security.config.annotation.web.configurers.
 * AbstractHttpConfigurer; import
 * org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder; import
 * org.springframework.security.crypto.password.PasswordEncoder; import
 * org.springframework.security.oauth2.client.registration.ClientRegistration;
 * import org.springframework.security.oauth2.client.registration.
 * ClientRegistrationRepository; import
 * org.springframework.security.oauth2.client.registration.
 * InMemoryClientRegistrationRepository; import
 * org.springframework.security.oauth2.core.AuthorizationGrantType; import
 * org.springframework.security.oauth2.core.ClientAuthenticationMethod; import
 * org.springframework.security.web.SecurityFilterChain; import
 * org.springframework.web.cors.CorsConfiguration;
 * 
 * @Configuration //@EnableWebSecurity public class WeshopifyAppSecurityConfig {
 * 
 * private static final String[] WHITE_LIST_URLS = { "/authn/token" };
 * 
 * //@Bean public ClientRegistrationRepository clientRegRepo() { return new
 * InMemoryClientRegistrationRepository(registerWithAuth0()); }
 * 
 * private ClientRegistration registerWithAuth0() { ClientRegistration clientReg
 * = ClientRegistration
 * .withRegistrationId("weshopify-platform-authentication-service")
 * .clientId("SZNLhhABJxc14D3v3Yfflf5ks6Oy12V9") .clientSecret(
 * "fbuXhbJIHYKyz4Bn9kdDV4_p9jczkUYMoa86vnNA8e5xG9eYbCBtTwTJCp1xDZpS")
 * .authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
 * .clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_BASIC).
 * scope("email", "profile")
 * .authorizationUri("https://dev-c7g863j05da8mwwv.us.auth0.com/authorize")
 * .tokenUri("https://dev-c7g863j05da8mwwv.us.auth0.com/oauth/token")
 * .userInfoUri("https://dev-c7g863j05da8mwwv.us.auth0.com/userinfo")
 * .redirectUri("https://weshopifyapp.com:5020/authn/token")
 * .scope(Arrays.asList("email","profile")) .build(); return clientReg; }
 * 
 * 
 * @Bean public SecurityFilterChain securityFilterChain(HttpSecurity http)
 * throws Exception { http.cors(AbstractHttpConfigurer::disable);
 * http.csrf(AbstractHttpConfigurer::disable);
 * http.authorizeHttpRequests(request -> {
 * request.requestMatchers(WHITE_LIST_URLS).permitAll();
 * request.anyRequest().authenticated(); })
 * .oauth2Login(Customizer.withDefaults()); return http.build(); }
 * 
 * 
 * // @Bean public SecurityFilterChain configure(HttpSecurity http) throws
 * Exception { http.cors(Customizer.withDefaults())
 * .authorizeHttpRequests(authorize -> authorize
 * .requestMatchers("/authn/token/**").permitAll() .anyRequest().authenticated()
 * ) .oauth2Login(Customizer.withDefaults()); return http.build(); }
 * 
 * //@Bean public PasswordEncoder passwordEncoder() { return new
 * BCryptPasswordEncoder(11); }
 * 
 * }
 */