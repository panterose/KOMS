package org.panterose.oms.oms

import com.samskivert.mustache.Mustache
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.web.reactive.config.EnableWebFlux
import com.samskivert.mustache.Mustache.TemplateLoader

@SpringBootApplication
class OmsApplication {
    /*
    @Bean
    fun mustacheCompiler(templateLoader: TemplateLoader) =
            // TODO Find a way to disable HTML escaping before enabling user authentication
            Mustache.compiler().escapeHTML(false).withLoader(templateLoader)
            */

}

fun main(args: Array<String>) {
    SpringApplication.run(OmsApplication::class.java, *args)
}