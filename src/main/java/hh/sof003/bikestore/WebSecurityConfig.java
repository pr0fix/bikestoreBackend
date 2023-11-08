package hh.sof003.bikestore;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import hh.sof003.bikestore.services.AccountDetailServiceImpl;

import static org.springframework.boot.autoconfigure.security.servlet.PathRequest.toH2Console;
//antMatcher
import static org.springframework.security.web.util.matcher.AntPathRequestMatcher.antMatcher;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

@Configuration
@EnableMethodSecurity(securedEnabled = true)
public class WebSecurityConfig {

	@Autowired
	private AccountDetailServiceImpl accountDetailService;

	@Bean
	public SecurityFilterChain configure(HttpSecurity http) throws Exception {
		http.cors(cors -> {
			CorsConfiguration configuration = new CorsConfiguration();
			configuration.setAllowedOrigins(Arrays.asList("http://localhost:5173"));
			configuration.setAllowedMethods(Arrays.asList("GET"));
			configuration.setAllowedHeaders(List.of("*"));
			UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
			source.registerCorsConfiguration("/**", configuration);
			cors.configurationSource(request -> configuration);
		})
				.authorizeHttpRequests(authorize -> authorize
						.requestMatchers(antMatcher("/css/**")).permitAll() // allows css file to be used without login
						.requestMatchers(antMatcher("/signup")).permitAll() // allows all users to see signup-page
						.requestMatchers(antMatcher("/saveaccount")).permitAll()
						.requestMatchers(antMatcher("/api/products")).permitAll()
						.requestMatchers(toH2Console()).permitAll() // allows all users to see saveuser-page
						.anyRequest().authenticated() // any other request needs authentication
				)
				.csrf(csrf -> csrf
						.ignoringRequestMatchers(toH2Console()))
				.headers(headers -> headers
						.frameOptions(frameoptions -> frameoptions
								.disable()))
				.formLogin(formlogin -> formlogin
						.loginPage("/login")
						.defaultSuccessUrl("/productlist", true)
						.permitAll())
				.logout(logout -> logout
						.permitAll());

		return http.build();
	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(accountDetailService).passwordEncoder(new BCryptPasswordEncoder());
	}

}