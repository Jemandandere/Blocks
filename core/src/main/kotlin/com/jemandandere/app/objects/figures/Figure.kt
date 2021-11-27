package com.jemandandere.app.objects.figures

import com.badlogic.gdx.graphics.g2d.Batch
import com.jemandandere.app.consts.Sizes
import com.jemandandere.app.objects.Block

abstract class Figure(val pos: IntArray) {

    abstract var xPos: Int
    abstract var yPos: Int

    fun draw(batch: Batch) {
        pos.forEachIndexed { index, pos ->
            val block = Block()
            val x = (xPos + pos / 2) * Sizes.blockSize
            val y = (yPos + pos % 2) * Sizes.blockSize
            block.draw(batch, x, y)
        }
    }
}