package org.panterose.oms.oms

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.web.server.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity
import org.springframework.security.authorization.AuthorityAuthorizationManager.hasRole
import org.springframework.security.web.server.util.matcher.ServerWebExchangeMatchers.anyExchange
import org.springframework.security.web.server.SecurityWebFilterChain
import org.springframework.security.core.userdetails.MapUserDetailsRepository
import org.springframework.security.core.userdetails.User.withUsername
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.authorization.AuthorizationDecision
import org.springframework.security.web.server.authorization.AuthorizationContext
import org.springframework.security.core.Authentication
import org.springframework.security.core.userdetails.User
import reactor.core.publisher.Mono



@Configuration
@EnableWebFluxSecurity
class WebSecurityConfig {

    @Bean
    @Throws(Exception::class)
    fun springWebFilterChain(http: HttpSecurity): SecurityWebFilterChain {
        return http
                .authorizeExchange()
                    .pathMatchers("/", "/home").permitAll()
                    .anyExchange().authenticated()
                .and()
                /*    .formLogin()
                .loginPage("/login")
                .permitAll()
                .and()
                .logout()
                .permitAll()
                */
                .build()

    }


    private fun currentUserMatchesPath(authentication: Mono<Authentication>, context: AuthorizationContext): Mono<AuthorizationDecision> {
        return authentication
                .map { a -> context.variables["user"] == a.name }
                .map { granted -> AuthorizationDecision(granted!!) }
    }

    @Bean
    fun userDetailsRepository(): MapUserDetailsRepository {
        val rob = User.withUsername("rob").password("rob").roles("USER").build()
        val admin = User.withUsername("admin").password("admin").roles("USER", "ADMIN").build()
        return MapUserDetailsRepository(rob, admin)
    }
}