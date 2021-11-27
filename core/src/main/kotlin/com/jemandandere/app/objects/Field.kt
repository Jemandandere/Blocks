package com.jemandandere.app.objects

import com.badlogic.gdx.graphics.g2d.Batch
import com.jemandandere.app.consts.Sizes.blockSize
import com.jemandandere.app.objects.figures.J
import com.jemandandere.logic.Parameters

class Field(private val width: Int = Parameters.fieldWidth, height: Int = Parameters.fieldHeight) {

    private val figure = J(5, 5)
    private val blocks = mutableListOf<Block?>()

    init {
        repeat(width * height) {
            blocks.add(null)
        }
    }

    fun draw(batch: Batch, x: Float, y: Float) {
        blocks.forEachIndexed { i, block ->
            block?.let {
                val xPos = x + (i % width) * blockSize
                val yPos = y + (i / width) * blockSize
                it.draw(batch, xPos, yPos)
            }
        }
        figure.draw(batch)
    }

    fun disposeSafely() {
        blocks.forEach { block ->
            block?.disposeSafely()
        }
    }
}