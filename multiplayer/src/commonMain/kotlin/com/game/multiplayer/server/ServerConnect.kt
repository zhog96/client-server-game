package com.game.multiplayer.server

import com.game.multiplayer.connect.Connect
import com.game.multiplayer.connect.ConnectedEntity
import com.game.multiplayer.connect.EntitiesState

class ServerConnect(
    private val clientApi: ClientApi
): Connect {
    val serverState: EntitiesState = EntitiesState()

    override fun register(entity: ConnectedEntity) {
        TODO("Not yet implemented")
    }

    override fun sync() {
        TODO("Not yet implemented")
    }
}