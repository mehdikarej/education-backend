package crocoder.webteam.edukate.config;


	import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;
import org.springframework.web.cors.CorsConfiguration;
	import org.springframework.web.cors.CorsConfigurationSource;
	import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import crocoder.webteam.edukate.security.JwtFilter;
import crocoder.webteam.edukate.servicesImpl.UserDetailsServiceImpl;
	@Configuration
	@EnableWebSecurity(debug=true)
	@Component
	public class SecurityConfig {

		@Autowired
		JwtFilter jwtFilter;

		@Autowired
		UserDetailsServiceImpl userDetailsService;
		@Bean
		public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
			http.csrf(csrf -> csrf.disable()).cors(cors -> cors.configurationSource(corsConfigurationSource()))
					.authorizeHttpRequests(
							auth -> auth.requestMatchers("/error","/api/auth/**","/api/users/**","/api/courses/**","/api/classes/**","/api/evaluation/**").permitAll().anyRequest().authenticated())
					.sessionManagement(manager -> manager.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
					.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

			return http.build();
		}

		@Bean
		public DaoAuthenticationProvider authenticationProvider() {
			DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
			authProvider.setUserDetailsService(userDetailsService);
			authProvider.setPasswordEncoder(passwordEncoder());
			return authProvider;
		}

		@Bean
		public PasswordEncoder passwordEncoder() {
			return new BCryptPasswordEncoder();
		}

		@Bean
		public CorsConfigurationSource corsConfigurationSource() {
			CorsConfiguration config = new CorsConfiguration();
			config.setAllowedOrigins(List.of("http://localhost:4200"));
			config.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
			config.setAllowedHeaders(List.of("Authorization", "Content-Type", "*"));
			config.setExposedHeaders(List.of("Authorization"));
			config.setAllowCredentials(true);

			UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
			source.registerCorsConfiguration("/**", config);
			return source;
		}

		@Bean
		public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
			return http.getSharedObject(AuthenticationManagerBuilder.class).authenticationProvider(authenticationProvider())
					.build();
		}
}
