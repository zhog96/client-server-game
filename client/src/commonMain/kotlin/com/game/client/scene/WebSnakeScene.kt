package com.game.client.scene

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import com.game.client.ktorwebclient.ktorWebSocketClient
import com.game.client.scene.model.WebSnakeState
import io.ktor.http.cio.websocket.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.suspendCancellableCoroutine
import java.util.concurrent.ConcurrentLinkedQueue
import kotlin.coroutines.Continuation
import kotlin.coroutines.resume

class WebSnakeScene {
    private val mapper = ObjectMapper()
    private val webSocket = ktorWebSocketClient.createWebSocket {
        try {
            val listener = launch {
                for (frame in incoming) {
                    webState = mapper.readValue<List<WebSnakeState>>(frame.data.decodeToString())
                }
            }
            val sender = launch {
                while (true) {
                    while (sendQueue.isNotEmpty()) {
                        val request = sendQueue.remove()
                        send(Frame.byType(false, FrameType.TEXT, request.body.toByteArray()))
                        request.compete()
                    }
                    delay(10)
                }
            }
            listener.join()
            sender.join()
        } catch (e: Exception) {
            println("Error while receiving messages: $e")
        }
    }

    @Volatile
    private var webState: List<WebSnakeState> = emptyList()
    private val sendQueue = ConcurrentLinkedQueue<Request>()

    suspend fun spawn(position: Pair<Double, Double>) {
        suspendCancellableCoroutine {
            sendQueue.add(Request(it, mapper.writeValueAsString(position)))
        }
    }

    fun getState(): List<WebSnakeState> = webState

    private class Request(
        private val continuation: Continuation<Unit>,
        val body: String
    ) {
        fun compete() {
            continuation.resume(Unit)
        }
    }
}