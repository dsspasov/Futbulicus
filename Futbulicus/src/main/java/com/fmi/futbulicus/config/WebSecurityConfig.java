//package com.fmi.futbulicus.config;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.builders.WebSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
//
//import com.fmi.futbulicus.model.User;
//import com.fmi.futbulicus.repository.UserRepository;
//
//@Configuration
//@EnableWebSecurity
//public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
//
//	@Autowired
//	private UserRepository userRepository;
//
//	@Autowired
//	public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {
//		auth.userDetailsService(userDetailsService()).passwordEncoder(passwordEncoder());
//	}
//	
//	@Bean
//	public PasswordEncoder passwordEncoder() {
//		return new BCryptPasswordEncoder();
//	}
//	
//	@Bean
//	protected UserDetailsService userDetailsService() {
//		return new UserDetailsService() {
//			@Override
//			public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
//				User user = userRepository.findByUsername(name);
//				if (user != null) {
//					return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), getGrantedAuthorities(user));
//				} else {
//					throw new UsernameNotFoundException("could not find user with email '" + name + "'");
//				}
//			}
//		};
//	}
//
//	@Override
//	protected void configure(HttpSecurity http) throws Exception {
//		http.csrf().disable();
//		http.authorizeRequests()
//		.antMatchers("/", "/index", "/home", "/register", "/login", "/footballers",
//				"/footballers/footballer", "/matches", "/matches/match", "/teams", "/teams/team", "/").permitAll()
//		.antMatchers("/users").access("hasRole('ROLE_USER')")
//		.anyRequest().authenticated()
//		.and()
//		.formLogin()
//			.usernameParameter("username")
//			.passwordParameter("password")
//			.loginPage("/login")
//			.defaultSuccessUrl("/home", true)
//			.failureUrl("/login?status=error")
//		.permitAll()
//		.and()
//		  .logout()
//		  	.deleteCookies("JSESSIONID")
//		  	.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
//		  	.logoutSuccessUrl("/login?status=logout")
//		  	.invalidateHttpSession(true)
//		 .permitAll()
//		 .and()
//		 .exceptionHandling().accessDeniedPage("/403");
//	  }
//	
//	@Override
//	public void configure(WebSecurity web) throws Exception {
//		web.ignoring().antMatchers("/css/*").antMatchers("/img/*").antMatchers("/js/*");
//	}
//	 
//	private List<GrantedAuthority> getGrantedAuthorities(User user) {
//		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
//		for(GrantedAuthority a: authorities){
//			System.out.println(a.getAuthority());
//		}
//		authorities.add(new SimpleGrantedAuthority("ROLE_" + user.getRole()));
//		return authorities;
//	}
//	
//}
