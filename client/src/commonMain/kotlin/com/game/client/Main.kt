package com.game.client

import com.game.client.scene.SnakeScene
import com.game.multiplayer.ClientPlayer
import korlibs.image.color.Colors
import korlibs.korge.Korge
import korlibs.korge.scene.sceneContainer
import korlibs.math.geom.Size

suspend fun main() {
    Korge(windowSize = Size(1024, 768), backgroundColor = Colors["#2b2b2b"]) {
        val sceneContainer = sceneContainer()
        sceneContainer.changeTo { SnakeScene() }
    }
}