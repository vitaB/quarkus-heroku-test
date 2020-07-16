package com.webhook.bot

import org.apache.camel.quarkus.kotlin.routes
import org.eclipse.microprofile.config.inject.ConfigProperty
import javax.enterprise.context.ApplicationScoped
import javax.enterprise.inject.Produces


@ApplicationScoped
class TelegramRoute() {

    @ConfigProperty(name = "webhook.external.url")
    lateinit var webhookUrl: String


    @ConfigProperty(name = "telegram.token")
    lateinit var telegramToken: String

    @Produces
    fun myRoutes() = routes {
        from("webhook:telegram:bots?webhookExternalUrl=$webhookUrl&authorizationToken=$telegramToken")
                .log("\${body}")
    }
}