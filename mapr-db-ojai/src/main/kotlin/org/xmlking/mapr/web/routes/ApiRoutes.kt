package org.xmlking.mapr.web.routes

import org.xmlking.mapr.*
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.DependsOn
import org.springframework.http.MediaType.*
import org.springframework.web.reactive.function.server.router


@Configuration
class ApiRoutes(val userHandler: UserHandler) {
    @Bean
    @DependsOn("databaseInitializer")
    fun apiRouter() = router {
        (accept(APPLICATION_JSON) and "/api").nest {

            // users
            "/users".nest {
                GET("/", userHandler::list)
                GET("/{id}", userHandler::get)
                POST("/", userHandler::create)
                PUT("/", userHandler::update)
                DELETE("/{id}", userHandler::delete)
            }
        }
    }
}