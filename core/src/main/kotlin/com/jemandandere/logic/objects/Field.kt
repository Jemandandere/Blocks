package com.jemandandere.logic.objects

import com.jemandandere.logic.Parameters
import com.jemandandere.logic.objects.figures.Figure

class Field(val width: Int = Parameters.fieldWidth, height: Int = Parameters.fieldHeight) {

    private val _map = mutableListOf<Boolean>()
    val map: List<Boolean>
        get() = getActualMap()

    private var figure: Figure? = null

    private var timer: Float = 0f

    init {
        repeat(width * height) { _map.add(false) }
        createFigure()
    }

    fun update(delta: Float) {
        timer += delta
        if (timer < SECOND) return
        timer -= SECOND
        figure?.let { fig ->
            if (fig.yPos == Parameters.fieldHeight - 2) {
                fig.pos.forEach { i ->
                    val x = (fig.xPos + i / 2)
                    val y = (fig.yPos + i % 2)
                    _map.set(y * width + x, true)
                    createFigure()
                }
            } else {
                figure?.yPos = fig.yPos + 1
            }
        }
    }

    private fun getActualMap() = _map.toMutableList().apply {
        figure?.let { fig ->
            fig.pos.forEach { i ->
                val x = (fig.xPos + i / 2)
                val y = (fig.yPos + i % 2)
                set(y * width + x, true)
            }
        }
    }

    private fun createFigure() {
        figure = Figure.getRandom(START_X_POS, START_Y_POS)
    }

    companion object {

        private const val START_X_POS = 5
        private const val START_Y_POS = 0

        private const val SECOND = 0.5f
    }
}