
package com.game.server

import io.ktor.http.cio.websocket.*
import java.util.concurrent.atomic.AtomicInteger

class PlayerConnection(val session: DefaultWebSocketSession) {
    companion object {
        val ID = AtomicInteger()
    }

    val id = ID.getAndIncrement()

    override fun toString(): String {
        return "PlayerConnection(id=$id)"
    }
}