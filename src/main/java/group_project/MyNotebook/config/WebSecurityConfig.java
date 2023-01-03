package group_project.MyNotebook.config;

import group_project.MyNotebook.user.MyUserJdbcDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.SecurityFilterChain;

@SuppressWarnings({"unused", "DefaultAnnotationParam", "ClassCanBeRecord"})
@RequiredArgsConstructor
@EnableWebSecurity
@Configuration
@EnableMethodSecurity(
        prePostEnabled = true,
        securedEnabled = true,
        jsr250Enabled = true)
public class WebSecurityConfig {
    private final NamedParameterJdbcTemplate jdbcTemplate;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .headers(headers -> headers.frameOptions(HeadersConfigurer.FrameOptionsConfig::disable))
                .authorizeHttpRequests()
                .requestMatchers("/", "/register/**", "/login", "/css/**", "/js/**", "/img/**", "/resources/**")
                .permitAll()
                .requestMatchers("/admin/**").hasAuthority("ADMIN")
                .anyRequest().authenticated()
                .and()
                .formLogin(form -> form
                        .usernameParameter("email")
                        .loginPage("/login")
                        .permitAll()
                        .defaultSuccessUrl("/note/list",true))
                .logout()
                .permitAll().deleteCookies()
        ;
        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return new MyUserJdbcDetailsService(jdbcTemplate);
    }
}
