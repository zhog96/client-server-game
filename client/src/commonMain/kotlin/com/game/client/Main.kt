package com.game.client

import com.game.client.controll.LocalControl
import com.game.client.controll.LocalControl.Companion.localControl
import com.game.client.movable.snake.Snake
import com.game.client.movable.snake.Snake.Companion.snake
import korlibs.time.*
import korlibs.korge.*
import korlibs.korge.scene.*
import korlibs.korge.view.*
import korlibs.image.color.*
import korlibs.math.geom.*
import java.util.concurrent.atomic.AtomicBoolean

suspend fun main() = Korge(windowSize = Size(1024, 768), backgroundColor = Colors["#2b2b2b"]) {
    val sceneContainer = sceneContainer()

    sceneContainer.changeTo { MyScene() }
}

class MyScene : Scene() {
    private val launched = AtomicBoolean(false)
    private val snakes = ArrayList<Snake>()
    private lateinit var control: LocalControl

    override suspend fun SContainer.sceneInit() {
        addUpdater { deltaTime ->
            if (launched.get()) update(deltaTime.milliseconds)
        }
        val snake = snake(Vector2D(100, 100))
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