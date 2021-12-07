package kr.pco.infra;

import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;


@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebConfiguration extends WebSecurityConfigurerAdapter   {
	 @Override
	    public void configure(WebSecurity web) throws Exception {
	      web.ignoring().antMatchers("/v2/api-docs",
                  "/configuration/ui",
                  "/swagger-resources/**",
                  "/configuration/security",
                  "/swagger-ui.html",
                  "/h2-console/**",
                  "/webjars/**");
	    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

    	http
    	.csrf().disable()
    	.authorizeRequests()
      .antMatchers(HttpMethod.POST ,"/**").permitAll()
      .antMatchers(HttpMethod.PUT ,"/**").permitAll()
      .antMatchers(HttpMethod.DELETE ,"/**").permitAll()
      .antMatchers(HttpMethod.GET,"/**").permitAll();

        
    }
}