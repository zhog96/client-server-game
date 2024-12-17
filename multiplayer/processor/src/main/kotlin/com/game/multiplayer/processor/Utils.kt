package com.game.multiplayer.processor

import java.io.OutputStream

internal fun OutputStream.appendText(str: String) {
    this.write(str.toByteArray())
}