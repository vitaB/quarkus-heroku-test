package com.webhook.bot

import com.github.kittinunf.fuel.core.responseUnit
import com.github.kittinunf.fuel.httpGet
import org.apache.camel.builder.endpoint.EndpointRouteBuilder
import org.eclipse.microprofile.config.inject.ConfigProperty
import javax.enterprise.context.ApplicationScoped


@ApplicationScoped
class TelegramRoute(): EndpointRouteBuilder() {

    @ConfigProperty(name = "webhook.external.url")
    lateinit var webhookUrl: String


    @ConfigProperty(name = "telegram.token")
    lateinit var telegramToken: String


    @Throws(Exception::class)
    override fun configure() {
        "https://api.telegram.org/bot$telegramToken/setWebhook?url=$webhookUrl/$telegramToken"
                .httpGet()
                .responseUnit()

        from("webhook:telegram:bots?authorizationToken=$telegramToken&webhookPath=$telegramToken&webhookAutoRegister=false")
                .log("\${body}")
    }
}