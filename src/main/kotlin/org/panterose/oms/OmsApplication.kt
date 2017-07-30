package org.panterose.oms

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

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