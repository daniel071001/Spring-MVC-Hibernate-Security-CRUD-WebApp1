package hibernate.config;

import hibernate.config.handler.LoginSuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;


@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final UserDetailsService userDetailsService;
    private final LoginSuccessHandler loginSuccessHandler;

    public SecurityConfig(@Qualifier("userDetailsServiceImpl") UserDetailsService userDetailsService, LoginSuccessHandler loginSuccessHandler) {
        this.userDetailsService = userDetailsService;
        this.loginSuccessHandler = loginSuccessHandler;
    }



    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider(){
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        authenticationProvider.setUserDetailsService(userDetailsService);
        return authenticationProvider;
    }
//
//    @Autowired
//    public void configureGlobalSecurity(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
//        auth.authenticationProvider(daoAuthenticationProvider());
//    }

    @Autowired
    public void configureGlobalSecurity(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.authorizeRequests()
//                .antMatchers("/", "/login").anonymous()
//                .antMatchers("/user").access("hasAnyRole('ROLE_USER, ROLE_ADMIN')")
//                .antMatchers("/admin/**").access("hasRole('ROLE_ADMIN')")
//                .anyRequest().authenticated();
//
//        http.formLogin()
//                .permitAll()
//                .successHandler(loginSuccessHandler)
//                .usernameParameter("j_username")
//                .passwordParameter("j_password");
//
//        http.logout()
//                .permitAll()
//                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
//                .logoutSuccessUrl("/")
//                .and().csrf().disable();
//    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/**").permitAll()
                .antMatchers("/login").permitAll()
                .antMatchers("/user").access("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
                .antMatchers("/admin").access("hasAnyRole('ROLE_ADMIN')")
                .antMatchers("/user-add").access("hasAnyRole('ROLE_ADMIN')")
                .antMatchers("/add-list").access("hasAnyRole('ROLE_ADMIN')")
                .antMatchers("/user-update/{id}").access("hasAnyRole('ROLE_ADMIN')")
                .antMatchers("/user-update").access("hasAnyRole('ROLE_ADMIN')")
                .antMatchers("user-delete/{id}").access("hasAnyRole('ROLE_ADMIN')")
                .and()
                .formLogin()
                .successHandler(loginSuccessHandler);
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }
}

