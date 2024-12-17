package com.game.multiplayer.client

import com.game.multiplayer.connect.EntitiesState

interface ServerApi {
    fun serverState(): EntitiesState
    fun offerState(state: EntitiesState)
}