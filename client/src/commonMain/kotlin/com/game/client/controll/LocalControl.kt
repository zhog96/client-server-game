package com.game.client.controll

import com.game.client.movable.Controllable
import korlibs.event.Key
import korlibs.korge.scene.Scene

class LocalControl(
    private val scene: Scene,
    private val controllable: Controllable
) {
    private val moment = 0.2

    fun update() {
        scene.run {
            if (input.keys.justPressed(Key.G)) controllable.grow()
            var totalMoment = 0.0
            if (input.keys.pressing(Key.A)) totalMoment -= moment
            if (input.keys.pressing(Key.D)) totalMoment += moment
            controllable.turn(totalMoment)
        }
    }

    companion object {
        fun Scene.localControl(controllable: Controllable) = LocalControl(this, controllable)
    }
}