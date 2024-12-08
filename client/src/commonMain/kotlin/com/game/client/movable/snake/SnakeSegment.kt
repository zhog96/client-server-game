package com.game.client.movable.snake

import com.game.client.Destroyable
import korlibs.korge.view.Circle
import korlibs.korge.view.SContainer
import korlibs.korge.view.circle

class SnakeSegment(
    scene: SContainer,
    private val size: Double,
    private val trajectory: SnakeTrajectory,
    val position: Double
): Destroyable {
    private val circle: Circle

    init {
        scene.run {
            circle = circle {
                radius = this@SnakeSegment.size
            }
        }
    }

    override fun destroy() {
        circle.removeFromParent()
    }

    fun update() {
        val position = trajectory.position(position)
        circle.x = position.x
        circle.y = position.y
    }
}