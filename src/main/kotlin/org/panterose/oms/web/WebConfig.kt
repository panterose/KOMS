package org.panterose.oms.web

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.thymeleaf.spring5.ISpringWebFluxTemplateEngine
import org.thymeleaf.spring5.view.reactive.ThymeleafReactiveViewResolver

@Configuration
class WebConfig(val templateEngine: ISpringWebFluxTemplateEngine) {

    @Bean
    fun thymeleafChunkedAndDataDrivenViewResolver() : ThymeleafReactiveViewResolver {
        val viewResolver = ThymeleafReactiveViewResolver()
        viewResolver.templateEngine = templateEngine
        viewResolver.order = 1
        viewResolver.responseMaxChunkSizeBytes = 8192 // OUTPUT BUFFER size limit
        return viewResolver

    }
}