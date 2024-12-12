package main.kotlin.com.game.server.game.movable.snake

import com.game.server.game.movable.Controllable
import korlibs.math.geom.Angle
import korlibs.math.geom.Vector2D
import korlibs.math.geom.degrees

class Snake(
    private var position: Vector2D
) : Controllable {
    private var snakeSpeed: Double = 0.2
    private val segmentSize: Double = 60.0
    private var direction: Angle = 0.degrees
    private var moment: Double = 0.0
    val segments = mutableListOf<SnakeSegment>()
    private var length: Double = 0.0
    private val trajectory = SnakeTrajectory(position)

    init {
        createHead()
        (1..2).forEach { _ -> addBodySegment() }
    }

    fun update(deltaTime: Double) {
        direction += (moment * deltaTime).degrees
        position += Vector2D(direction.cosine, direction.sine) * snakeSpeed * deltaTime
        trajectory.update(length, position)
        segments.forEach { it.update() }
    }

    override fun turn(moment: Double) {
        this.moment = moment
    }

    override fun grow() {
        addBodySegment()
    }

    private fun createHead() {
        segments.add(SnakeSegment(segmentSize, trajectory, 0.0))
    }

    private fun addBodySegment() {
        val parent = segments.last()
        segments.add(
            SnakeSegment(
                segmentSize,
                trajectory,
                parent.snakePosition + segmentSize / 2
            )
        )
        length += segmentSize / 2
    }
}