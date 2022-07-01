package ar.edu.unju.fi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import ar.edu.unju.fi.service.imp.LoginService;

@Configuration
@EnableWebSecurity
public class ConfigurationWeb extends WebSecurityConfigurerAdapter{
  @Autowired
	private Autentication autenticacion;

	String[] resources = new String[] { "/include/**", "/css/**", "/icons/**", "/img/**", "/js/**", "/layer/**","/webjars/**" };

  @Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable()
			.authorizeRequests()
				.antMatchers(resources).permitAll()
				.antMatchers("/", "/home", "/index","/formulario").permitAll()
				.anyRequest().authenticated()
				.and()
			.formLogin()
				.loginPage("/login")
				.permitAll()
				.successHandler(autenticacion)
				.failureUrl("/login?error=true")
				.usernameParameter("dni")
				.passwordParameter("contraseña")				
				.and()
			.logout()
				.permitAll()
				.logoutSuccessUrl("/login?logout");
	}	

	BCryptPasswordEncoder bCryptPasswordEncoder;

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder(4);
	}

	@Autowired
	LoginService userDetailsService;

	@Autowired

	protected void GlobalConfiguration(AuthenticationManagerBuilder authentication) throws Exception {
    System.out.println("****inicio del usuario*****");
		authentication.userDetailsService(userDetailsService);
	}

}
