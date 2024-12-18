package com.game.multiplayer.model

import com.game.multiplayer.processor.Synchronized
import korlibs.math.geom.Vector2D

@Synchronized
interface PlayerEntity {
    val position: Vector2D
}