package com.game.multiplayer.ktorwebclient

import io.ktor.client.*
import io.ktor.client.features.websocket.*

object KtorWebSocketClient {
    val client by lazy {
        HttpClient {
            install(WebSockets)
        }
    }
}