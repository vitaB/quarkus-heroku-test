package com.webhook.bot

import org.apache.camel.builder.RouteBuilder
import org.apache.camel.component.webhook.WebhookEndpoint
import org.eclipse.microprofile.config.inject.ConfigProperty
import javax.enterprise.context.ApplicationScoped


@ApplicationScoped
class TelegramRoute(@ConfigProperty(name = "quarkus.http.port")
                    val port: Int): RouteBuilder() {

    @ConfigProperty(name = "telegram.token")
    lateinit var telegramToken: String

    override fun configure() {

        restConfiguration()
                .host("localhost")
                .port(port)

        from("webhook:telegram:bots?authorizationToken=$telegramToken&webhookPath=$telegramToken&webhookAutoRegister=false")
                .to("telegram:bots?authorizationToken=$telegramToken")
    }
}