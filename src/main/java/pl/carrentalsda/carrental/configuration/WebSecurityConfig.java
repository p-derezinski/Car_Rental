package pl.carrentalsda.carrental.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    DataSource dataSource;

    @Autowired
    PasswordEncoder bCryptPasswordEncoder;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                // tutaj są URL wymagające autoryzacji - strefa chroniona
                // .antMatchers(/url) -> wymaga autoryzacji
                // .hasAnyAuthority("uprawnienie") -> dla określonego uprawnienia

                // pozostałe URL udostępnij dla każdego
                .anyRequest().permitAll()
                .and()
                .csrf().disable()
                // formularz logowania
                .formLogin()
                // adres formularza logowania
                .loginPage("/login")
                // nazwa pola w formularzu dot. loginu
                .usernameParameter("email")
                // nazwa pola w formularzu dot. hasła
                .passwordParameter("password")
                // adres gdzie przekazywane są parametry formularza
                .loginProcessingUrl("/login-process")
                // domyśly URL po poprawnym zalogowaniu
                .defaultSuccessUrl("/")
                // domyśly URL po błędnym logowaniu
                .failureUrl("/errorLogin")
                .and()
                .logout()
                // adres do wylogowania
                .logoutUrl("/logout")
                // miejsce po wylogowaniu
                .logoutSuccessUrl("/");
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .jdbcAuthentication()
                // SQL dla logowania użytkownika po adresie email i haśle
                .usersByUsernameQuery("SELECT u.email, u.password FROM users u WHERE u.email = ?")
                // SQL dla przypisania uprawnień dla zalogowanego użytkownika
                .authoritiesByUsernameQuery("SELECT u.email, r.role_name FROM users u JOIN users_role ur ON ur.users_id = u.id JOIN role r ON ur.role_id = r.id WHERE u.email = ?")
                // wynik logowania
                .dataSource(dataSource)
                // szyfrowanie hasła
                .passwordEncoder(bCryptPasswordEncoder);
    }
}