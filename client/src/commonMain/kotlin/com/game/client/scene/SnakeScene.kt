package com.game.client.scene

import com.game.client.game.controll.LocalControl
import com.game.client.game.controll.LocalControl.Companion.localControl
import com.game.client.game.movable.snake.Snake
import korlibs.korge.scene.Scene
import korlibs.korge.view.SContainer
import korlibs.korge.view.addUpdater
import korlibs.math.geom.Vector2D
import korlibs.time.milliseconds
import java.util.concurrent.atomic.AtomicBoolean

class SnakeScene : Scene() {
    private val launched = AtomicBoolean(false)
    private val snakes = ArrayList<Snake>()
    private lateinit var control: LocalControl

    override suspend fun SContainer.sceneInit() {
        addUpdater { deltaTime ->
            if (launched.get()) update(deltaTime.milliseconds)
        }
        val snake = Snake(Vector2D(100, 100))
        addChild(snake)
        snakes.add(snake)
        control = localControl(snake)
    }

    override suspend fun SContainer.sceneMain() {
        launched.set(true)
    }

    private fun update(deltaTime: Double) {
        control.update()
        snakes.forEach { it.update(deltaTime) }
    }
}