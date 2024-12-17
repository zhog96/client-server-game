package com.game.multiplayer.client

import com.game.multiplayer.connect.Action
import com.game.multiplayer.connect.EntitiesState

interface ServerApi {
    fun serverState(): EntitiesState
    fun offerState(state: EntitiesState)
    fun pushAction(action: Action)
}