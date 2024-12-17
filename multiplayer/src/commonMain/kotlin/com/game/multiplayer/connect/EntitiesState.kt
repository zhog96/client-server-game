package com.game.multiplayer.connect

import java.util.concurrent.ConcurrentHashMap


class EntitiesState {
    private val entities = ConcurrentHashMap<Long, ConnectedEntity>()
}