package org.panterose.oms.web

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.DependsOn
import org.springframework.core.io.ClassPathResource
import org.springframework.web.reactive.function.server.router
import org.springframework.web.reactive.function.server.ServerResponse.*
import org.springframework.web.reactive.function.server.RouterFunctions.resources
import org.springframework.http.MediaType.*

@Configuration
class WebsiteRoutes {
    @Bean
    @DependsOn("websiteRouter")
    fun resourceRouter() = resources("/**", ClassPathResource("static/"))

    @Bean
    fun websiteRouter(welcomeHandler: WelcomeHandler,
                      userHandler: UserHandler,
                      productHandler: ProductHandler,
                      orderHandler: OrderHandler
                      ) = router {
        GET("/", welcomeHandler::welcome)
        "/users".nest {
            GET("/", userHandler::all)
            "/add".nest {
                GET("/", userHandler::addPage)
                POST("/", userHandler::add)
            }
            "/view".nest {
                GET("/", userHandler::view)
            }
        }
    }
}
