package lt.techin.running.club.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http
            .authorizeHttpRequests((authorize) -> authorize
                    .requestMatchers(HttpMethod.POST, "/api/auth/register").permitAll()

                    .requestMatchers(HttpMethod.GET, "/api/events").hasAuthority("ROLE_USER")
                    .requestMatchers(HttpMethod.GET, "/api/events/**").hasAuthority("ROLE_USER")
                    .requestMatchers(HttpMethod.POST, "/api/events").hasAuthority("ROLE_ADMIN")
                    .requestMatchers(HttpMethod.DELETE, "/api/events/**").hasAuthority("ROLE_ADMIN")

                    .requestMatchers(HttpMethod.GET, "/api/users").hasAuthority("ROLE_ADMIN")
                    .requestMatchers(HttpMethod.GET, "/api/users/**").hasAuthority("ROLE_ADMIN")

                    .anyRequest().authenticated()
            )
            .csrf(c -> c.disable())
            .httpBasic(Customizer.withDefaults());
    return http.build();
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }
}
