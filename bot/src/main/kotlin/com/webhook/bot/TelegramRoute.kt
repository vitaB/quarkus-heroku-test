package com.webhook.bot

import org.apache.camel.quarkus.kotlin.routes
import javax.enterprise.context.ApplicationScoped
import javax.enterprise.inject.Produces


@ApplicationScoped
class TelegramRoute() {

    @Produces
    fun myRoutes() = routes {
        from("webhook:telegram:bots")
                .log("\${body}")
    }
}