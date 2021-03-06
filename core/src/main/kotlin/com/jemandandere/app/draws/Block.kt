package com.jemandandere.app.draws

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.Batch
import com.jemandandere.app.consts.Sizes
import ktx.assets.toInternalFile

abstract class Block(filepath: String) : Texture(filepath.toInternalFile(), true) {

    fun draw(batch: Batch, x: Float, y: Float) {
        batch.draw(this, x, y, Sizes.blockSize, Sizes.blockSize)
    }
}