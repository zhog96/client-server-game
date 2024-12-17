package com.game.multiplayer.client

import com.game.multiplayer.connect.Connect
import com.game.multiplayer.connect.ConnectedEntity
import com.game.multiplayer.connect.EntitiesState

class ClientConnect(
    private val serverApi: ServerApi
): Connect {
    val localState: EntitiesState = EntitiesState()

    override fun register(entity: ConnectedEntity) {
        TODO("Not yet implemented")
    }

    override fun sync() {
        TODO("Not yet implemented")
    }
}