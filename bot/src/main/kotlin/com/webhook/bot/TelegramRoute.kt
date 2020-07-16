package com.webhook.bot

import com.github.kittinunf.fuel.core.responseUnit
import com.github.kittinunf.fuel.httpGet
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
        "https://api.telegram.org/bot$telegramToken/setWebhook?url=$webhookUrl"
                .httpGet()
                .responseUnit()

        from("webhook:telegram:bots?authorizationToken=$telegramToken&webhookExternalUrl=$webhookUrl&webhookAutoRegister=false")
                .log("\${body}")
    }
}