package com.game.server.game.movable

interface Controllable {
    fun turn(moment: Double)

    fun grow()
}