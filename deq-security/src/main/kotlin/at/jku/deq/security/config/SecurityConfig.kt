package at.jku.deq.security.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.web.SecurityFilterChain

@EnableWebSecurity
@Configuration
internal class SecurityConfig {

    @Bean
    fun web(http: HttpSecurity): SecurityFilterChain {
        return http.authorizeHttpRequests {
            it.anyRequest().permitAll()
        }
            .csrf { it.disable() }
            .build()
    }
}