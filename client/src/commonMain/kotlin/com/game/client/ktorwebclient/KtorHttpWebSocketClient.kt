package com.game.client.ktorwebclient

import com.game.client.ktorwebclient.KtorWebSocketClient.client
import io.ktor.client.features.websocket.*
import io.ktor.http.*
import korlibs.io.async.launch
import kotlinx.coroutines.asCoroutineDispatcher
import java.util.concurrent.ForkJoinPool

class KtorHttpWebSocketClient(
    private val host: String,
    private val port: Int
) {
    fun createWebSocket(block: suspend DefaultClientWebSocketSession.() -> Unit) {
        launch(webSocketDispatcher) {
            client.ws(
                method = HttpMethod.Get,
                host = host,
                port = port,
                block = block
            )
        }
    }

    companion object {
        private val webSocketDispatcher by lazy { ForkJoinPool(3).asCoroutineDispatcher() }
    }
}