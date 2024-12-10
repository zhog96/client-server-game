package com.game.client.ktorwebclient

import io.ktor.client.features.websocket.*
import io.ktor.http.cio.websocket.*
import kotlinx.coroutines.launch

fun ktorWebSocketJob(): suspend DefaultClientWebSocketSession.() -> Unit = {
    try {
        launch {
            for (message in incoming) {
                println("Received frame!")
                when (message) {
                    is Frame.Binary -> TODO()
                    is Frame.Text -> {
                        val text = message.readText()
                        println("Got message: $text")
                    }

                    is Frame.Close -> TODO()
                    is Frame.Ping -> TODO()
                    is Frame.Pong -> TODO()
                }
            }
        }.join()
    } catch (e: Exception) {
        println("Error while receiving messages: $e")
    }
}

val ktorWebSocketClient = KtorHttpWebSocketClient("127.0.0.1", 8080)