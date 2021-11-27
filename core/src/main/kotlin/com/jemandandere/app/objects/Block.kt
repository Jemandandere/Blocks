package com.jemandandere.app.objects

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.Batch
import com.jemandandere.app.consts.Sizes
import ktx.assets.disposeSafely
import ktx.assets.toInternalFile

class Block {

    private val texture = Texture("block.png".toInternalFile(), true).apply {
        setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear)
    }

    fun draw(batch: Batch, x: Float, y: Float) {
        batch.draw(texture, x, y, Sizes.blockSize, Sizes.blockSize)
    }

    fun disposeSafely() {
        texture.disposeSafely()
    }
}