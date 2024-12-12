package com.game.client.ktorwebclient

import io.ktor.client.features.websocket.*
import io.ktor.http.cio.websocket.*
import kotlinx.coroutines.launch

fun ktorWebSocketJob(): suspend DefaultClientWebSocketSession.() -> Unit = {
    try {
        launch {
            for (frame in incoming) {
                println("Got message: ${frame.data.decodeToString()}")
            }
        }.join()
    } catch (e: Exception) {
        println("Error while receiving messages: $e")
    }
}

val ktorWebSocketClient = KtorHttpWebSocketClient("127.0.0.1", 8080)