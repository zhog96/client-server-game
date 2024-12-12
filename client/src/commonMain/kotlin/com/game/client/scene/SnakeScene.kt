package com.game.client.scene

import com.game.client.game.movable.snake.Snake
import com.game.client.game.movable.snake.SnakeSegment
import com.game.client.scene.model.WebSnakeState
import korlibs.korge.scene.Scene
import korlibs.korge.view.SContainer
import korlibs.korge.view.addUpdater
import korlibs.math.geom.Vector2D
import korlibs.time.milliseconds
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.concurrent.atomic.AtomicBoolean
import kotlin.properties.Delegates

class SnakeScene : Scene() {
    private val webSnakeScene = WebSnakeScene()
    private val launched = AtomicBoolean(false)
    private var snakes: Map<Int, Snake> = HashMap()

    override suspend fun SContainer.sceneInit() {
        addUpdater { deltaTime ->
            if (launched.get()) {
                update(webSnakeScene.getState())
            }
        }
        webSnakeScene.spawn(100.0 to 100.0)
    }

    override suspend fun SContainer.sceneMain() {
        launched.set(true)
    }

    private fun SContainer.update(webState: List<WebSnakeState>) {
        val webSnakeIds = webState.map { it.id }.toSet()
        snakes.filterKeys { it !in webSnakeIds }.forEach { (_, snake) -> removeChild(snake) }
        snakes = snakes.filterKeys { it in webSnakeIds }
        snakes = snakes + webState.mapNotNull {
            if (it.id !in snakes) {
                it.id to Snake(
                    it.segments.map { s ->
                        SnakeSegment(s.size, Vector2D(s.position.first, s.position.second))
                    }
                )
            } else null
        }.toMap()
        webState.forEach {
            val snake = snakes[it.id]!!
            snake.segments = it.segments.map { s ->
                SnakeSegment(s.size, Vector2D(s.position.first, s.position.second))
            }
        }
    }
}