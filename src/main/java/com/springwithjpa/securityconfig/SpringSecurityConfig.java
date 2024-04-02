package com.springwithjpa.securityconfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.pulsar.PulsarProperties.Authentication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@Configuration
public class SpringSecurityConfig {

	@Autowired
	private CustomUserDetailService customUserDetailService;

	AuthenticationManager auth;
//	@Bean
//	InMemoryUserDetailsManager setupUser() {
//		UserDetails user1 = User.withUsername("nikita").password("nikita").roles("USER").build();
//		UserDetails user2 = User.withUsername("lisha").password("lisha").roles("USER", "ADMIN").build();
//		return new InMemoryUserDetailsManager(user1, user2);
//	}
//	

	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		authProvider.setUserDetailsService(customUserDetailService);
		authProvider.setPasswordEncoder(passwordEncoder());

		return authProvider;
	}

	private void configure(AuthenticationManagerBuilder auth) throws Exception {
		// TODO Auto-generated method stub
		auth.authenticationProvider(authenticationProvider());
	}

	@Bean
	PasswordEncoder passwordEncoder() {
		return  new BCryptPasswordEncoder();
	}

	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.csrf(csrf -> csrf.disable()).authorizeHttpRequests(customizer -> {
			customizer.requestMatchers(HttpMethod.POST, "/admin/add").hasRole("ADMIN");
			customizer.requestMatchers(HttpMethod.GET, "/user/*").hasAnyRole("ADMIN", "USER");
			customizer.requestMatchers(HttpMethod.GET, "/welcome").permitAll();
			customizer.anyRequest().permitAll();
		}).formLogin(Customizer.withDefaults()).httpBasic(Customizer.withDefaults());
		return http.build();

	}

}
