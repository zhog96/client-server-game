package com.game.client.movable

import com.game.client.Destroyable

interface Controllable: Destroyable {
    fun turn(moment: Double)

    fun grow()
}