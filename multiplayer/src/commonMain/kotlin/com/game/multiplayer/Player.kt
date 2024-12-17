package com.game.multiplayer

import com.game.multiplayer.processor.Synchronized
import korlibs.math.geom.Vector2D

@Synchronized
interface Player {
    val position: Vector2D
}