package com.totalPlay.app.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


//configuracion  la seguridad 
@Configuration
@EnableWebSecurity //con esta anotacion 
public class SecurityConfig  extends WebSecurityConfigurerAdapter{
@Autowired
private UserDetailsServiceimpl userDetails;

//para traer la contraseña encriptada
@Bean
public BCryptPasswordEncoder PasswordEncoder() {
	
	return new BCryptPasswordEncoder();
}
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// TODO Auto-generated method stub
		//autotentificar 
		
		
		auth.userDetailsService(userDetails).passwordEncoder(PasswordEncoder());
	}

	//se configuran las url publico y las privada
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// TODO Auto-generated method stub 
		//aqui  son las url dominio public
	http.authorizeHttpRequests().antMatchers("/","/auth/**","/public/**","/css/**","/js/**").permitAll().anyRequest().authenticated()
	.and()
	//de domino privado
	.formLogin().loginPage("/auth/login").defaultSuccessUrl("/private/index",true).failureUrl("/auth/login?error=true")
	.loginProcessingUrl("/auth/login-post").permitAll()
	.and()
	.logout().logoutUrl("/logout").logoutSuccessUrl("/public/index")
	.and()
	.csrf().disable().cors();
	}

}
