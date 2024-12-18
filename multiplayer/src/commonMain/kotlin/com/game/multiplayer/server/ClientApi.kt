package com.game.multiplayer.server

import com.game.multiplayer.Player
import com.game.multiplayer.state.EntitiesState

interface ClientApi {
    fun players(): List<Player>
    fun playerState(player: Player): EntitiesState
    fun pushState(player: Player, state: EntitiesState)
}