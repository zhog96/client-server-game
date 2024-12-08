package com.game.client.movable.snake

import com.game.client.movable.Controllable
import korlibs.korge.view.SContainer
import korlibs.math.geom.Angle
import korlibs.math.geom.Vector2D
import korlibs.math.geom.degrees

class Snake(
    private val scene: SContainer,
    private var position: Vector2D
) : Controllable {
    private var speed: Double = 0.2
    private val size: Double = 30.0
    private var direction: Angle = 0.degrees
    private var moment: Double = 0.0
    private val segments = mutableListOf<SnakeSegment>()
    private var length: Double = 0.0
    private val trajectory = SnakeTrajectory(position).also { scene.addChild(it) }

    init {
        createHead()
        (1..2).forEach { _ -> addBodySegment() }
    }

    fun update(deltaTime: Double) {
        direction += (moment * deltaTime).degrees
        position += Vector2D(direction.cosine, direction.sine) * speed * deltaTime
        trajectory.update(length, position)
        segments.forEach { it.update() }
    }

    override fun turn(moment: Double) {
        this.moment = moment
    }

    override fun grow() {
        addBodySegment()
    }

    override fun destroy() {
        segments.forEach { it.destroy() }
        scene.removeChild(trajectory)
    }

    private fun createHead() {
        segments.add(SnakeSegment(scene, size, trajectory, 0.0))
    }

    private fun addBodySegment() {
        val parent = segments.last()
        segments.add(
            SnakeSegment(
                scene,
                size,
                trajectory,
                parent.position + size
            )
        )
        length += size
    }

    companion object {
        fun SContainer.snake(position: Vector2D) = Snake(this, position)
    }
}