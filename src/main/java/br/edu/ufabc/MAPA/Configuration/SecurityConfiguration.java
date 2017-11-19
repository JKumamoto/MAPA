package br.edu.ufabc.MAPA.Security;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;


// The WebSecurityConfig class is annotated with @EnableWebSecurity
// to enable Spring Security’s web security support and provide the
// Spring MVC integration. It also extends WebSecurityConfigurerAdapter
// and overrides a couple of its methods to set some specifics of
// the web security configuration.

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter{

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
	private DataSource dataSource;

	private String userQuery="select email, senha from Entidade where email=?";

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception{
		auth.
			jdbcAuthentication()
				.dataSource(dataSource)
				.usersByUsernameQuery(userQuery)
				.passwordEncoder(bCryptPasswordEncoder);
	}

	// The configure(HttpSecurity) method defines which URL paths
	// should be secured and which should not. 
	@Override
	protected void configure(HttpSecurity http) throws Exception{
		http
			.authorizeRequests()
				.antMatchers("/").permitAll()
				.anyRequest().authenticated()
				.and()
			.formLogin()
				.loginPage("/login")
				.permitAll()
				.and()
			.logout()
				.permitAll();
	}
	 
	// Libera os recursos dentro desses caminhos, caso algum seja removido
	// somente um usuario autenticado poderia acessa-los.
	@Override
	public void configure(WebSecurity web) throws Exception{
		web
			.ignoring()
			.antMatchers("/css/**", "/js/**", "/jquery/**", "/bootstrap-3.3.7/**", "/images/**");
	}

}

