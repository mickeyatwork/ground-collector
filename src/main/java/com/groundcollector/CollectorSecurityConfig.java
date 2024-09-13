package com.groundcollector;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class CollectorSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private DataSource datasource;

    @Override
    protected void configure(final HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/anonymous*").anonymous()
                .antMatchers("/aboutUs*").permitAll()
                .antMatchers("/error*").permitAll()
                .antMatchers("/account*").permitAll()
                .antMatchers("/login*").permitAll()
                .antMatchers("/assets/css/**", "assets/js/**", "/images/**").permitAll()
                .antMatchers("/index*").permitAll()
                .antMatchers("/newEntry*").authenticated()
                .antMatchers("/travelLog*").authenticated()
                .antMatchers("/menu*").authenticated()
                .antMatchers("/map*").authenticated()
                .antMatchers("/editEntry*").authenticated()
                .antMatchers("/deleteEntry*").authenticated()
                .antMatchers("/groundChecklist*").authenticated()
                .antMatchers("/admin*").authenticated()
                .antMatchers("/admin*").hasAuthority("ADMIN")
                .antMatchers("/admin/**").hasAuthority("ADMIN")
                .antMatchers("/groundsUpdate*").authenticated()
                .antMatchers("/teamsUpdate*").authenticated()
                .antMatchers("/map*").authenticated()

                .and()
                .formLogin()
                .loginPage("/login")
                .loginProcessingUrl("/perform_login")
                .failureUrl("/login?error=true")
                .permitAll()
                .defaultSuccessUrl("/menu", true)

                .and()
                .rememberMe()
                .key("MoOsrpPssE4C9LERJhBmNblQs6id2XsJ")
                .tokenRepository(tokenRepository())

                .and()
                .logout()
                .logoutSuccessUrl("/login?logout=true")
                .logoutRequestMatcher(new AntPathRequestMatcher("/perform_logout", "GET"))
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID")
                .permitAll()
                ;
    }

    @Bean
    public PersistentTokenRepository tokenRepository() {
        JdbcTokenRepositoryImpl token = new JdbcTokenRepositoryImpl();
        token.setDataSource(datasource);
        return token;
    }


    @Override
    protected void configure(final AuthenticationManagerBuilder auth) throws Exception {

        auth.jdbcAuthentication().dataSource(datasource);

    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
