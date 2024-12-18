package com.game.multiplayer.client

import com.game.multiplayer.state.Action
import com.game.multiplayer.state.EntitiesState
import com.game.multiplayer.ktorwebclient.KtorHttpWebSocketClient.Companion.webSocketDispatcher
import com.game.multiplayer.ktorwebclient.KtorWebSocketClient
import io.ktor.client.features.websocket.*
import io.ktor.http.*
import io.ktor.http.cio.websocket.*
import korlibs.io.async.launch
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class ServerApiWebSocket(
    host: String,
    port: Int
): ServerApi {

    init {
        launch(webSocketDispatcher) {
            KtorWebSocketClient.client.ws(
                method = HttpMethod.Get,
                host = host,
                port = port,
                block = { socket() }
            )
        }
    }

    @Volatile
    private var serverState: EntitiesState = EntitiesState()

    override fun serverState(): EntitiesState = serverState

    override fun offerState(state: EntitiesState) {
        TODO("Not yet implemented")
    }

    override fun pushAction(action: Action) {
        TODO("Not yet implemented")
    }

    private suspend fun DefaultClientWebSocketSession.socket() {
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
}