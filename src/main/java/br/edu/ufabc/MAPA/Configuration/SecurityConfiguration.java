package br.edu.ufabc.MAPA.Configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

// The WebSecurityConfig class is annotated with @EnableWebSecurity
// to enable Spring Security’s web security support and provide the
// Spring MVC integration. It also extends WebSecurityConfigurerAdapter
// and overrides a couple of its methods to set some specifics of
// the web security configuration.

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter{

	@Autowired
	private UserDetailsService userDetailsService;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

	// The configure(HttpSecurity) method defines which URL paths
	// should be secured and which should not. 
	@Override
	protected void configure(HttpSecurity http) throws Exception{
		http
			.authorizeRequests()
				.antMatchers("/", "/registrar", "/login",  "/entidade", "/searchResults").permitAll()
				.anyRequest().authenticated()
				.and()
			.formLogin()
				.loginPage("/login")
				.and()
			.logout()
				.permitAll()
				.and()
			.csrf().disable();
	}
	 
	// Libera os recursos dentro desses caminhos, caso algum seja removido
	// somente um usuario autenticado poderia acessa-los.
	@Override
	public void configure(WebSecurity web) throws Exception{
		web
			.ignoring()
			.antMatchers("/css/**", "/js/**", "/jquery/**", "/bootstrap-3.3.7/**", "/images/**");
	}

	@Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder());
    }

}

