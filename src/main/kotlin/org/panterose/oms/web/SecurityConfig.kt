package org.panterose.oms.web

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.web.server.HttpSecurity;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity
import org.springframework.security.web.server.SecurityWebFilterChain
import org.springframework.security.core.userdetails.MapUserDetailsRepository
import org.springframework.security.authorization.AuthorizationDecision
import org.springframework.security.web.server.authorization.AuthorizationContext
import org.springframework.security.core.Authentication
import org.springframework.security.core.userdetails.User
import reactor.core.publisher.Mono
import org.springframework.security.core.userdetails.UserDetails


@Configuration
@EnableWebFluxSecurity
class WebSecurityConfig {
    val users = mutableMapOf<String, UserDetails>("admin" to User.withUsername("admin").password("admin").roles("USER", "ADMIN").build())

    @Bean
    @Throws(Exception::class)
    fun springWebFilterChain(http: HttpSecurity): SecurityWebFilterChain {
        return http
                .authorizeExchange()

                    .pathMatchers("/").permitAll()
                    .pathMatchers("/css/*").permitAll()
                    .pathMatchers("/users", "/users/*").authenticated().Access().access(this::currentUserMatchesPath)
                    .pathMatchers("/orders", "/orders/*").access(this::currentUserMatchesPath)
                    .pathMatchers("/products", "/products/*").access(this::currentUserMatchesPath)
                    .anyExchange().authenticated()
                .and()

                .build()

    }


    private fun currentUserMatchesPath(authentication: Mono<Authentication>, context: AuthorizationContext): Mono<AuthorizationDecision> {
        return authentication
                .map { a ->
                    println("user=$context / auth=$a")
                    context.variables["user"] == a.name }
                .map { granted ->
                    println("Granted or not? $granted")
                    AuthorizationDecision(granted!!)
                }
    }

    @Bean
    fun userDetailsRepository(): MapUserDetailsRepository {
        return MapUserDetailsRepository(users.withDefault { k ->  User.withUsername(k).password(k).roles("USER").build() })
    }
}