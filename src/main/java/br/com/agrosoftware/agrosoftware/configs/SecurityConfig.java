package br.com.agrosoftware.agrosoftware.configs;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import br.com.agrosoftware.agrosoftware.security.JWTAuthenticationFilter;
import br.com.agrosoftware.agrosoftware.security.JWTAuthorizationFilter;
import br.com.agrosoftware.agrosoftware.security.JWTUtil;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;
    
    @Autowired
    private Environment env;
    
    @Autowired
    private JWTUtil jwtUtil;
    
    private static final String[] PUBLIC_MATCHERS = {
            "/h2-console/**"
    };
    
    private static final String[] PUBLIC_MATCHERS_GET = {
            "/usuarios/**"
    };
    
    private static final String[] PUBLIC_MATCHERS_POST = {
    };
    
    private static final String[] PRIVATE_MATCHERS_GET = {
    };
    
    private static final String[] PRIVATE_MATCHERS_MODIFY = {
            "/usuarios/**"
    };

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        if (List.of(env.getActiveProfiles()).contains("test")) 
            http.headers().frameOptions().disable();
        
        http.cors().and()
            .csrf().disable()
            .authorizeRequests()
            .antMatchers(HttpMethod.GET, PUBLIC_MATCHERS_GET).permitAll()
            .antMatchers(HttpMethod.POST, PUBLIC_MATCHERS_POST).permitAll()
            .antMatchers(HttpMethod.GET, PRIVATE_MATCHERS_GET).hasAnyRole("ADMIN", "OPERADOR", "USUARIO")
            .antMatchers(HttpMethod.POST, PRIVATE_MATCHERS_MODIFY).hasAnyRole("ADMIN", "OPERADOR")
            .antMatchers(HttpMethod.PUT, PRIVATE_MATCHERS_MODIFY).hasAnyRole("ADMIN", "OPERADOR")
            .antMatchers(HttpMethod.DELETE, PRIVATE_MATCHERS_MODIFY).hasAnyRole("ADMIN", "OPERADOR")
            .antMatchers(HttpMethod.PUT, "/instituicoes/**").hasRole("ADMIN")
            .antMatchers(HttpMethod.DELETE, "/instituicoes/**").hasRole("ADMIN")
            .antMatchers(PUBLIC_MATCHERS).permitAll()
            .anyRequest().authenticated().and()
            .addFilter(new JWTAuthenticationFilter(authenticationManager(), jwtUtil))
            .addFilter(new JWTAuthorizationFilter(authenticationManager(), jwtUtil, userDetailsService))
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }
    
    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder());
    }
    
    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        final var source = new UrlBasedCorsConfigurationSource();
        var config = new CorsConfiguration().applyPermitDefaultValues();
        config.setAllowedMethods(List.of("GET", "POST", "DELETE"));
        config.setExposedHeaders(List.of("Authorization"));
        
        source.registerCorsConfiguration("/**", config);
        return source;
    }
    
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}