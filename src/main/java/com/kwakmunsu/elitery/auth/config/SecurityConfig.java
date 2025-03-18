package com.kwakmunsu.elitery.auth.config;


import com.kwakmunsu.elitery.auth.jwt.filter.JwtFilter;
import com.kwakmunsu.elitery.auth.jwt.handler.JwtAccessDeniedHandler;
import com.kwakmunsu.elitery.auth.jwt.handler.JwtAuthenticationEntryPoint;
import com.kwakmunsu.elitery.auth.oauth2.handler.CustomOAuth2FailureHandler;
import com.kwakmunsu.elitery.auth.oauth2.handler.CustomOAuth2SuccessHandler;
import com.kwakmunsu.elitery.auth.oauth2.service.CustomOAuth2UserService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    private final CustomOAuth2UserService customOAuth2UserService;
    private final CustomOAuth2SuccessHandler customSuccessHandler;
    private final CustomOAuth2FailureHandler customOauth2FailureHandler;
    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    private final JwtAccessDeniedHandler jwtAccessDeniedHandler;
    private final JwtFilter jwtFilter;
    private final String[] adminUrl = {"/admin/**"};
    private final String[] permitAllUrl = {
        "/",
        "/oauth2/**",
        "/auth/**", "/swagger/**", "/swagger-ui/**", "/v3/api-docs/**"
    };
    private final String[] hasRoleUrl = {
    };

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .csrf(AbstractHttpConfigurer::disable)
            .formLogin(AbstractHttpConfigurer::disable)
            .httpBasic(AbstractHttpConfigurer::disable)
            .sessionManagement(session -> session
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        http
            .oauth2Login(oauth2 -> oauth2
                .userInfoEndpoint( //  **사용자 정보(UserInfo)**를 가져오는 엔드포인트를 설정하는 부분
                    userInfoEndpointConfig -> userInfoEndpointConfig
                        .userService(customOAuth2UserService))
                .successHandler(customSuccessHandler)
                .failureHandler(customOauth2FailureHandler)
            );

        http
            .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

        http
            .authorizeHttpRequests(auth -> auth
                .requestMatchers(permitAllUrl).permitAll()
                .requestMatchers(adminUrl).hasRole("ADMIN")
                .requestMatchers(hasRoleUrl).hasAnyRole("ADMIN", "MEMBER")
                .anyRequest().authenticated());

        http
            .exceptionHandling(handle -> handle
                .authenticationEntryPoint(jwtAuthenticationEntryPoint)
                .accessDeniedHandler(jwtAccessDeniedHandler));

        http
            .cors(
                corsCustomizer -> corsCustomizer.configurationSource(request -> {
                    CorsConfiguration config = new CorsConfiguration();

                    config.setAllowedOrigins(List.of("http://localhost:3000"));
                    config.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE"));
                    config.setAllowedHeaders(List.of("*"));
                    config.setAllowCredentials(true);
                    config.setMaxAge(3600L);
                    config.setExposedHeaders(List.of("Set-Cookie", "Authorization"));
                    return config;
                })
            );
        return http.build();
    }

}