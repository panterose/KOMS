package org.panterose.oms.oms

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.server.router
import org.springframework.web.reactive.function.server.ServerResponse.*
import java.net.URI
import org.springframework.http.MediaType.*

@Configuration
class WebsiteRoutes {


    @Bean
    fun websiteRouter() = router {
        accept(TEXT_HTML).nest {
            GET("/") { ok().contentType(TEXT_HTML).render("index", mapOf(Pair("title", "mixteen.title")))}
            GET("/home") { ok().contentType(TEXT_HTML).render("home", mapOf(Pair("title", "mixteen.title"))) }
        }
    }
        //GET("/home") { ok.permanentRedirect("${properties.baseUri}/") }
        /*
        GET("/blog/feed", blogHandler::feed)
        accept(TEXT_HTML).nest {
            GET("/") { sponsorHandler.viewWithSponsors("home", null, it) }
            GET("/about", globalHandler::findAboutView)

        }

    }
    */



}
