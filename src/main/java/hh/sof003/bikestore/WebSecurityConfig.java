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
import static org.springframework.boot.autoconfigure.security.servlet.PathRequest.toH2Console;
import static org.springframework.security.web.util.matcher.AntPathRequestMatcher.antMatcher;
import org.springframework.beans.factory.annotation.Autowired;

import hh.sof003.bikestore.services.AccountDetailServiceImpl;

import java.util.Arrays;
import java.util.List;

@Configuration
@EnableMethodSecurity(securedEnabled = true)
public class WebSecurityConfig {

	@Autowired
	private AccountDetailServiceImpl accountDetailService;

	private static final List<String> ALLOWED_URLS = Arrays.asList(
			"/css/**",
			"/signup",
			"/saveaccount",
			"/api/products",
			"/api/products/{productId}",
			"/productlist",
			"/api/categories",
			"/api/categories/{categoryId}",
			"/h2-console/**");

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
				.authorizeHttpRequests(authorize -> {
					for (String pattern : ALLOWED_URLS) {
						authorize.requestMatchers(antMatcher(pattern)).permitAll();
					}
					authorize.anyRequest().authenticated();
				})
				.csrf(csrf -> csrf
						.ignoringRequestMatchers("/h2-console/**"))
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