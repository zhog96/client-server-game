package com.game.client.game.movable.snake

import korlibs.korge.render.RenderContext
import korlibs.korge.view.View

class Snake(var segments: List<SnakeSegment>) : View() {
    override fun renderInternal(ctx: RenderContext) {
        ctx.useBatcher { batcher ->
            segments.forEach { it.render(ctx, batcher) }
        }
    }
}