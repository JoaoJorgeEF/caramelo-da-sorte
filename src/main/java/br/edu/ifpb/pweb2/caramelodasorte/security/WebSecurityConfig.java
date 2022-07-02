package br.edu.ifpb.pweb2.caramelodasorte.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
@EnableAutoConfiguration
//@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    DataSource dataSource;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers("/css/**", "/imagens/**").permitAll()
                .antMatchers("/apostadores").hasRole("ADMIN")
                .anyRequest().authenticated().and()
                .formLogin(form -> form.loginPage("/auth").defaultSuccessUrl("/home", true).permitAll()).logout(logout -> logout.logoutUrl("/auth/logout"));
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        auth.jdbcAuthentication()
                .dataSource(dataSource)
                .passwordEncoder(encoder);
//                .withUser(User.builder().username("joao").password(encoder.encode("joao123")).roles("CLIENTE").build())
//                .withUser(User.builder().username("admin").password(encoder.encode("admin123"))
//                        .roles("CLIENTE", "ADMIN").build());
        // .withUser(
        // User.builder().username("turing").password(encoder.encode("enignma")).roles("CLIENTE").build())
        // .withUser(User.builder().username("sagan").password(encoder.encode("cosmos")).roles("CLIENTE").build());


    }

    @Bean
    @Override
    protected UserDetailsService userDetailsService() {
        UserDetails user = User.withDefaultPasswordEncoder().username("teste").password("teste").roles("ADM").build();
        return new InMemoryUserDetailsManager(user);
    }
}
