package com.game.client.game.movable.snake

import KR
import korlibs.image.bitmap.*
import korlibs.korge.render.BatchBuilder2D
import korlibs.korge.render.RenderContext
import kotlinx.coroutines.runBlocking

class SnakeSegment(
    private val size: Double,
    private val trajectory: SnakeTrajectory,
    val snakePosition: Double
) {
    private var position = trajectory.position(snakePosition)

    fun render(ctx: RenderContext, batcher: BatchBuilder2D) {
        val size = size.toFloat()
        batcher.drawQuad(
            ctx.getTex(circleBitmap),
            x = position.x.toFloat(), y = position.y.toFloat(),
            width = size, height = size
        )
    }

    fun update() {
        position = trajectory.position(snakePosition)
    }

    companion object {
        private val circleBitmap = runBlocking { KR.circle.readSlice() }
    }
}