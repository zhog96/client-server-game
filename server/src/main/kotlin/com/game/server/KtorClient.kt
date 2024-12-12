package com.game.server

import io.ktor.application.*
import io.ktor.features.*
import io.ktor.http.*
import io.ktor.http.ContentType.Application.Json
import io.ktor.http.cio.websocket.*
import io.ktor.response.*
import io.ktor.routing.*
import io.ktor.websocket.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.lang.System.nanoTime
import java.time.Duration
import java.time.LocalDateTime
import java.util.concurrent.ConcurrentHashMap

fun Application.configureHTTP() {
    install(DefaultHeaders) {
        header("X-Engine", "Ktor") // will send this header with each response
    }
    install(CORS) {
        method(HttpMethod.Options)
        method(HttpMethod.Put)
        method(HttpMethod.Delete)
        method(HttpMethod.Patch)
        header(HttpHeaders.Authorization)
        anyHost()
    }

}

fun Application.configureSockets() {
    install(WebSockets) {
        pingPeriod = Duration.ofSeconds(15)
        timeout = Duration.ofSeconds(15)
        maxFrameSize = Long.MAX_VALUE
        masking = false
    }

    val playerConnections = ConcurrentHashMap<Int, PlayerConnection>()

    launch {
        while(true) {
            playerConnections.forEach { (id, connection) ->
                runCatching { connection.session.send("$id ^^ ${nanoTime()}") }
            }
            delay(1000)
        }
    }

    routing {
        webSocket("/") {
            val playerConnection = PlayerConnection(this)
            playerConnections[playerConnection.id] = playerConnection
            println("Added player connection: $playerConnection")
            try {
                for (frame in incoming) {
                }
            } catch (e: Exception) {
                println(e.localizedMessage)
            } finally {
                playerConnections.remove(playerConnection.id)
                println("Removing $playerConnection")
            }
        }
    }
}