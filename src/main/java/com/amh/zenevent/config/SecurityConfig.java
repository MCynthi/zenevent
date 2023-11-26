package com.amh.zenevent.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.amh.zenevent.jwtFiles.JwtRequestFilter;
import com.amh.zenevent.service.AuthenticationService;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private JwtRequestFilter jwtRequestFilter;

	//@Autowired
	private AuthenticationEntryPoint entryPoint;

	@Autowired
	private AuthenticationService authenticationService;
	
	@Bean
	public static PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

//	@Override
//	protected void configure(HttpSecurity httpSecurity) throws Exception {
//		httpSecurity
//				.csrf().disable().authorizeRequests()
//				.antMatchers("/event/**").permitAll() 
//				.antMatchers("/api/user/authenticate").permitAll()
//				.antMatchers("/**").permitAll()
//				.antMatchers("/users/**","/agents/**").access("hasRole('ADMIN')").and() 
//				.authorizeRequests().anyRequest().authenticated().and().exceptionHandling()
//				.authenticationEntryPoint(entryPoint).and().sessionManagement()
//				.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
//
//		httpSecurity.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
//	}
	
	@Override
	public void configure(HttpSecurity http) throws Exception {
	      http
	      	 .csrf().disable()
	         .authorizeRequests()
	         .antMatchers("/api/**","/gestionnaire").permitAll()
	         .antMatchers("/","/event","/user","/visitor","/service","/support")
	         //.hasRole("ADMIN")
	         //.anyRequest()
	         .authenticated()
	         .and()
	         .formLogin()
	         .loginPage("/login")
	         .defaultSuccessUrl("/")
		 	 .and()
		 	 .logout().invalidateHttpSession(true);
	      http.exceptionHandling().accessDeniedPage("/errors/403");
	}
	
	@Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/v2/api-docs",
        							"/static/**",
                                   "/configuration/ui",
                                   "/swagger-resources/**",
                                   "/configuration/security",
                                   "/swagger-ui.html",
                                   "/swagger-ui.html#",
                                   "/webjars/**");
    }
	
	@Override
	protected void configure(final AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(authenticationService).passwordEncoder(passwordEncoder());
	}


	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

}

