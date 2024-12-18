package com.game.multiplayer.client

import com.game.multiplayer.state.EntitiesState
import kotlinx.coroutines.delay

class ClientConnect(
    private val serverApi: ServerApi
) {
    private val localState: EntitiesState = EntitiesState()

    suspend fun connect() {
        while (true) {
            sync()
            delay(100)
        }
    }

    private fun sync() {
        localState.set(serverApi.serverState())
    }
}