package com.game.multiplayer.connect

interface Connect {
    fun register(entity: ConnectedEntity)
    fun sync()
}