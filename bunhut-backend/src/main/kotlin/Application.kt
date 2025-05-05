package me.mantou

import io.ktor.http.HttpHeaders
import io.ktor.http.HttpMethod
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.plugins.cors.routing.*
import kotlinx.serialization.json.Json

fun main(args: Array<String>) {
    io.ktor.server.netty.EngineMain.main(args)
}

fun Application.module() {
    install(CORS) {
        anyHost()
        allowHeader(HttpHeaders.ContentType)
    }
    install(ContentNegotiation) {
        json(Json {
            encodeDefaults = true
            explicitNulls = false
        })
    }
    configureRouting()
}