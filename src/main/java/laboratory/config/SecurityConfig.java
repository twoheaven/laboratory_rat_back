package laboratory.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.header.writers.frameoptions.XFrameOptionsHeaderWriter;

// Spring Security 설정을 담당하는 클래스입니다.
@Configuration
@EnableWebSecurity
public class SecurityConfig {
    
    // 보안 필터 체인을 설정하는 빈을 생성합니다.
    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests((authorizeHttpRequests) -> authorizeHttpRequests
                // 모든 HTTP 요청을 허용합니다.
                .requestMatchers(new AntPathRequestMatcher("/**")).permitAll())
            .csrf((csrf) -> csrf
                    // H2 콘솔에 대한 CSRF 보호를 비활성화합니다.
                    .ignoringRequestMatchers(new AntPathRequestMatcher("/h2-console/**")))
            .headers((headers) -> headers
                    // X-Frame-Options 헤더를 설정하여 클릭재킹을 방지합니다.
                    .addHeaderWriter(new XFrameOptionsHeaderWriter(
                        XFrameOptionsHeaderWriter.XFrameOptionsMode.SAMEORIGIN)))
        ;
        return http.build(); // 설정된 보안 필터 체인을 반환합니다.
    }
}
