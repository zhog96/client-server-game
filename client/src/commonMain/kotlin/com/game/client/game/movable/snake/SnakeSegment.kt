package com.game.client.game.movable.snake

import KR
import korlibs.korge.render.BatchBuilder2D
import korlibs.korge.render.RenderContext
import korlibs.math.geom.Vector2D
import kotlinx.coroutines.runBlocking

class SnakeSegment(
    var size: Double,
    var position: Vector2D
) {
    fun render(ctx: RenderContext, batcher: BatchBuilder2D) {
        val size = size.toFloat()
        batcher.drawQuad(
            ctx.getTex(circleBitmap),
            x = position.x.toFloat(), y = position.y.toFloat(),
            width = size, height = size
        )
    }

    companion object {
        private val circleBitmap = runBlocking { KR.circle.readSlice() }
    }
}