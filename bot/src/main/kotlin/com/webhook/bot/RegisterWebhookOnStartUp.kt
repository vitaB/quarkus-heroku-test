package com.webhook.bot

import com.github.kittinunf.fuel.core.responseUnit
import com.github.kittinunf.fuel.httpGet
import io.quarkus.runtime.Startup
import org.eclipse.microprofile.config.inject.ConfigProperty
import javax.enterprise.context.ApplicationScoped

@Startup
@ApplicationScoped
class RegisterWebhookOnStartUp(@ConfigProperty(name = "webhook.external.url")
                               webhookUrl: String,
                               @ConfigProperty(name = "telegram.token")
                               telegramToken: String) {


    init {
        "https://api.telegram.org/bot$telegramToken/setWebhook?url=$webhookUrl/$telegramToken"
                .httpGet()
                .responseUnit()
    }
}