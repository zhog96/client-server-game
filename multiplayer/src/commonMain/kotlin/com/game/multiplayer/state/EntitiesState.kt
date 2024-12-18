package com.game.multiplayer.state

import java.util.concurrent.ConcurrentHashMap


class EntitiesState {
    private val entities = ConcurrentHashMap<Long, ConnectedEntity>()

    fun set(with: EntitiesState) {
        val currentKeys = entities.keys().toList().toSet()
        val withKeys = with.entities.keys().toList().toSet()
        (withKeys - currentKeys).forEach {
            // TODO register strategy for server and client
            with.entities[it]!!
        }
        (currentKeys - withKeys).forEach {
            // TODO remove strategy for server and client
            entities.remove(it)
        }
        currentKeys.intersect(withKeys).forEach {
            entities[it]!!.set(with.entities[it]!!)
        }
    }
}