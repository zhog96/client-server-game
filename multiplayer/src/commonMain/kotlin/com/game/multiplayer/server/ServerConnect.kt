package com.game.multiplayer.server

import com.game.multiplayer.state.EntitiesState
import kotlinx.coroutines.delay

class ServerConnect(
    private val clientApi: ClientApi
) {
    private val serverState: EntitiesState = EntitiesState()

    suspend fun connect() {
        while (true) {
            sync()
            delay(100)
        }
    }

    private fun sync() {
        clientApi.players().forEach {
            clientApi.pushState(it, serverState)
        }
    }
}