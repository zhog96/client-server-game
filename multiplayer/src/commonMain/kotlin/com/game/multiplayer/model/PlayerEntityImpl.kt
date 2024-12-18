package com.game.multiplayer.model

import com.game.multiplayer.state.ConnectedEntity
import korlibs.math.geom.Vector2D

internal class PlayerEntityImpl(
    position: Vector2D
) : ConnectedEntity, PlayerEntity {
    @Volatile
    override var position: Vector2D = position
        private set

    override fun set(with: ConnectedEntity) {
        position = (with as PlayerEntity).position
    }
}