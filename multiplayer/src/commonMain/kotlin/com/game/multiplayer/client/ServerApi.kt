package com.game.multiplayer.client

import com.game.multiplayer.state.Action
import com.game.multiplayer.state.EntitiesState

interface ServerApi {
    fun serverState(): EntitiesState
    fun offerState(state: EntitiesState)
    fun pushAction(action: Action)
}