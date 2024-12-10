package com.game.server

import io.ktor.server.engine.*
import io.ktor.server.cio.*

fun main() {
    embeddedServer(CIO, port = 8080, host = "0.0.0.0") {
        configureHTTP()
        configureSockets()
    }.start(wait = true)
}