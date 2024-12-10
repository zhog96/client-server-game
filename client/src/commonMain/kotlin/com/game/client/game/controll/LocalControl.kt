package com.game.client.game.controll

import com.game.client.game.movable.Controllable
import com.game.client.ktorwebclient.ktorWebSocketClient
import com.game.client.ktorwebclient.ktorWebSocketJob
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
            if (input.keys.justPressed(Key.C)) ktorWebSocketClient.createWebSocket(ktorWebSocketJob())
            controllable.turn(totalMoment)
        }
    }

    companion object {
        fun Scene.localControl(controllable: Controllable) = LocalControl(this, controllable)
    }
}