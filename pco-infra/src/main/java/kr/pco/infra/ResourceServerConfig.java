package kr.pco.infra;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.resource.ResourceServerProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

import kr.pco.core.spring.MyAccessDeniedHandler;
import kr.pco.core.spring.MyAuthenticationEntryPoint;  

/**
 * @author chano
 * @Description :
 */
@Configuration
@EnableResourceServer
class ResourceServerConfig extends ResourceServerConfigurerAdapter {

	
	 @Autowired
	 private ResourceServerProperties resourceServerProperties;
	
	  @Bean
	    public static PasswordEncoder passwordEncoder() {
	        return new BCryptPasswordEncoder();
	    }
	  @Bean
	    public JwtAccessTokenConverter accessTokenConverter() {
	        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
	        converter.setSigningKey(resourceServerProperties.getJwt().getKeyValue());
	        return converter;
	    }
	  
	    @Bean
	    public TokenStore tokenStore() {
	        return new JwtTokenStore(accessTokenConverter());
	    }
	@Override
	    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
		resources.authenticationEntryPoint(new MyAuthenticationEntryPoint());
		 resources.accessDeniedHandler(new MyAccessDeniedHandler());
	    	// TODO Auto-generated method stub
	    }    

	
    @Override
    public void configure(HttpSecurity http) throws Exception {
    	
    	http.authorizeRequests()
    	.antMatchers(HttpMethod.OPTIONS).permitAll()
    	  .antMatchers(HttpMethod.GET,"/member/**").access("#oauth2.hasScope('product.noset.read')")
    	  .antMatchers("/swagger-ui.html/**").permitAll()
    	  .antMatchers("/webjars/**").permitAll()
    	  .antMatchers("/swagger-resources/**").permitAll()
    	  .antMatchers("/v2/**").permitAll()
    	  .anyRequest().authenticated()
    	  .and()
    	  .csrf().disable();
    }
}