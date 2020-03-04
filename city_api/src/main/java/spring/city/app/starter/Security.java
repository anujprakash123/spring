package spring.city.app.starter;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SuppressWarnings("deprecation")
@EnableWebSecurity
public class Security extends WebSecurityConfigurerAdapter {
@Override
protected void configure(AuthenticationManagerBuilder auth) throws Exception {
	//edit the user and password for the roles
	auth.inMemoryAuthentication().withUser("user1").password("pass1").roles("user")
	.and().withUser("user2").password("pass2").roles("user")
	.and().withUser("admin").password("passcode").roles("admin");
	}
	@Bean
	public PasswordEncoder getPasswordEncoder() {
		return NoOpPasswordEncoder.getInstance();	
	}
	@Override
		protected void configure(HttpSecurity http) throws Exception {
			http.authorizeRequests()
			.antMatchers("/","/hello","/ping").permitAll()
			.antMatchers("/cities/{id}").hasAnyRole("user","admin")
			.antMatchers("/**").hasRole("admin").and().formLogin();
		}
}
