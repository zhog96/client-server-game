package main.kotlin.com.game.server.game.movable.snake

class SnakeSegment(
    private val size: Double,
    private val trajectory: SnakeTrajectory,
    val snakePosition: Double
) {
    var position = trajectory.position(snakePosition)

    fun update() {
        position = trajectory.position(snakePosition)
    }
}