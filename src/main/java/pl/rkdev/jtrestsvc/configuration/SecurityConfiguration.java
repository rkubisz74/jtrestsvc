package pl.rkdev.jtrestsvc.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.access.channel.ChannelProcessingFilter;
import org.springframework.security.web.session.HttpSessionEventPublisher;
import pl.rkdev.jtrestsvc.filters.CharsetFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Bean
	public HttpSessionEventPublisher httpSessionEventPublisher() {
	    return new HttpSessionEventPublisher();
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) 
			throws Exception {
		auth
			.inMemoryAuthentication()
			.withUser("user")
				.password("{noop}test")
				.roles("USER");
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.authorizeRequests()
			.antMatchers("/beers", "/shutdown").authenticated()
			.and()
			.httpBasic()
			.and()
			.addFilterBefore(new CharsetFilter(), ChannelProcessingFilter.class);
		http.csrf().disable();
	}
}
