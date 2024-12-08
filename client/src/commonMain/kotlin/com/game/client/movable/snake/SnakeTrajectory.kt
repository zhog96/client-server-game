package com.game.client.movable.snake

import korlibs.korge.render.RenderContext
import korlibs.korge.render.useLineBatcher
import korlibs.korge.view.View
import korlibs.math.geom.Vector2D
import korlibs.math.toIntFloor
import kotlin.math.roundToInt

class SnakeTrajectory(
    position: Vector2D
): View()  {
    private val edgeSize = 10.0

    private val points = ArrayDeque<Vector2D>()

    init {
        points.add(position)
        points.add(position)
        points.add(position)
    }

    fun update(length: Double, position: Vector2D) {
        points.removeLast()
        var leftLength = (position - points.last()).length
        val direction = (position - points.last()).normalized
        while (leftLength > edgeSize) {
            points.addLast(points.last() + direction * edgeSize)
            leftLength -= edgeSize
        }
        points.addLast(position)
        trim(length)
    }

    fun position(length: Double): Vector2D {
        val firstEdgeLength = firstEdgeLength
        val innerEdgesLength = innerEdgesLength
        val lastEdgeLength = lastEdgeLength
        if (length > firstEdgeLength + innerEdgesLength + lastEdgeLength) return points.first()
        if (length > innerEdgesLength + lastEdgeLength) {
            val pointFrom = points[1]
            val pointTo = points[0]
            return (pointTo - pointFrom).normalized * (length - innerEdgesLength - lastEdgeLength) + pointFrom
        }
        if (length > lastEdgeLength) {
            val idx = ((length - lastEdgeLength) / edgeSize).toIntFloor()
            val pointFrom = points[points.size - idx - 2]
            val pointTo = points[points.size - idx - 3]
            return (pointTo - pointFrom).normalized * (length - lastEdgeLength - idx * edgeSize) + pointFrom
        }
        val pointFrom = points[points.size - 1]
        val pointTo = points[points.size - 2]
        return (pointTo - pointFrom).normalized * length + pointFrom
    }

    private fun trim(length: Double) {
        val firstEdgeLength = firstEdgeLength
        val innerEdgesLength = innerEdgesLength
        val lastEdgeLength = lastEdgeLength
        var lengthToTrim = firstEdgeLength + innerEdgesLength + lastEdgeLength - length
        while (lengthToTrim > 0) {
            val first = points[0]
            points.removeFirst()
            val second = points[0]
            lengthToTrim -= (second - first).length
            if (lengthToTrim <= 0) {
                points.addFirst((second - first).normalized * lengthToTrim + second)
            }
        }
    }

    private val firstEdgeLength get() = (points[1] - points[0]).length
    private val innerEdgesLength get() = (points.size - 3) * edgeSize
    private val lastEdgeLength get() = (points[points.size - 1] - points[points.size - 2]).length

    override fun renderInternal(ctx: RenderContext) {
        return
        ctx.useLineBatcher { batcher ->
            (0..points.size - 2).forEach {
                batcher.line(
                    points[it].x.roundToInt(),
                    points[it].y.roundToInt(),
                    points[it + 1].x.roundToInt(),
                    points[it + 1].y.roundToInt()
                )
            }
        }
    }
}