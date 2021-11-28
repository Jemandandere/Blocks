package com.jemandandere.app.utils

import com.badlogic.gdx.graphics.g2d.Batch
import com.jemandandere.app.consts.Sizes.blockSize
import com.jemandandere.app.draws.Block
import com.jemandandere.logic.Parameters
import com.jemandandere.logic.objects.Field

fun Field.draw(batch: Batch, blocks: Map<Int, Block>, x: Float, y: Float) {
    this.map.forEachIndexed { i, color ->
        val xPos = x + (i % width) * blockSize
        val yPos = y + (Parameters.fieldHeight - 1 - i / width) * blockSize
        val block = blocks[color] ?: return
        block.draw(batch, xPos, yPos)
    }
}