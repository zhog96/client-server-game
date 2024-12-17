package com.game.multiplayer

import com.game.multiplayer.connect.Connect
import com.game.multiplayer.connect.ConnectedEntity
import korlibs.math.geom.Vector2D

class PlayerEntityImpl(
    @Volatile
    var position: Vector2D,
    connect: Connect
) : ConnectedEntity {
    init {
        connect.register(this)
    }
}